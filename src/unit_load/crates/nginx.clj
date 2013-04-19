(ns unit-load.crates.nginx
  (:require
   [clojure.tools.logging :as logging]
   [pallet.actions :as act]
   [pallet.api :as api]
   [pallet.crate :as crate]
   [pallet.crate-install :as crate-install]))

(defn default-settings
  []
  {:install-strategy :package-source
   :package-source
   {:name "debian-backports"
    :aptitude {:url "http://backports.debian.org/debian-backports"
               :release "squeeze-backports"
               :scopes ["main"]}}
   :packages ["nginx-full"]})

(crate/defplan settings
  "Settings for nginx"
  [{:keys [instance-id] :as settings}]
  (let [settings (merge (default-settings) settings)]
    (crate/assoc-settings :nginx settings {:instance-id instance-id})))

(crate/defplan install
  "Install nginx"
  [{:keys [instance-id]}]
  (let [settings (crate/get-settings :nginx {:instance-id instance-id})]
    (logging/debugf "Install nginx settings %s" settings)
    (crate-install/install :nginx instance-id)))

(defn server-spec
  "A server spec for nginx"
  [settings & {:keys [instance-id] :as options}]
  (api/server-spec
   :phases
   {:settings (api/plan-fn
               (unit-load.crates.nginx/settings (merge settings options)))
    :install (api/plan-fn (install options))
    :configure (api/plan-fn (act/service "nginx" :action :start))}))
