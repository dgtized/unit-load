(ns unit-load.groups.unit-load
  (:require
   [pallet.api :as api]
   [pallet.crate.automated-admin-user :refer [with-automated-admin-user]]
   pallet.crate.java
   pallet.crate.package.debian-backports
   [pallet.crate.etc-hosts :as etc-hosts]
   [pallet.actions :as act]))

(def default-node-spec
  (api/node-spec
   :image {:os-family :debian}
   :hardware {:min-cores 1}))

(def unit-load-server
  (api/server-spec
   :extends [(pallet.crate.java/server-spec {})]
   :phases
   {:bootstrap (api/plan-fn (etc-hosts/set-hostname))
    :configure
    (api/plan-fn
     (pallet.crate.package.debian-backports/add-debian-backports)
     (act/package-source "postgresql-pgdg" :aptitude
                         {:url "http://apt.postgresql.org/pub/repos/apt/"
                          :release "squeeze-pgdg"
                          :scopes ["main"]
                          :key-url "http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc"})
     (act/package-manager :update)
     (act/package "postgresql")
     (act/package "nginx-full" :enable "squeeze-backports")
     )}))

(def unit-load
  (api/group-spec
   "unit-load"
   :extends [with-automated-admin-user unit-load-server]
   :node-spec default-node-spec
   :count 1))
