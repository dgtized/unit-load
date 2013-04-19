(ns unit-load.crates.postgresql
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
   {:name "postgresql-pgdg"
    :aptitude {:url "http://apt.postgresql.org/pub/repos/apt/"
               :release "squeeze-pgdg"
               :scopes ["main"]
               :key-url "http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc"}}
   :packages ["postgresql"]
   :package-options {:enable "postgresql-pgdg"}})

(crate/defplan settings
  "Settings for postgresql"
  [{:keys [instance-id] :as settings}]
  (let [settings (merge (default-settings) settings)]
    (crate/assoc-settings :postgresql settings {:instance-id instance-id})))

(crate/defplan install
  "Install postgresql"
  [{:keys [instance-id]}]
  (let [settings (crate/get-settings :postgresql {:instance-id instance-id})]
    (logging/debugf "Install postgresql settings %s" settings)
    (crate-install/install :postgresql instance-id)))

(defn server-spec
  "A server spec for postgresql"
  [settings & {:keys [instance-id] :as options}]
  (api/server-spec
   :phases
   {:settings (api/plan-fn
               (unit-load.crates.postgresql/settings (merge settings options)))
    :install (api/plan-fn (install options))}))
