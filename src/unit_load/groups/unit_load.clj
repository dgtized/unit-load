(ns unit-load.groups.unit-load
  (:require
   [pallet.api :as api]
   [pallet.crate.automated-admin-user :refer [automated-admin-user]]
   pallet.crate.java
   [pallet.actions :as act]))

(def default-node-spec
  (api/node-spec
   :image {:os-family :debian}
   :hardware {:min-cores 1}))

(def base-server
  (api/server-spec
   :phases
   {:bootstrap (api/plan-fn
                (automated-admin-user)
                (act/package-manager :update))}))

(def unit-load-server
  (api/server-spec
   :extends [(pallet.crate.java/server-spec {})]
   :phases
   {:configure
    (api/plan-fn
     (act/package-source "postgresql-pgdg" :aptitude
                         {:url "http://apt.postgresql.org/pub/repos/apt/"
                          :release "squeeze-pgdg"
                          :scopes ["main"]
                          :key-url "http://apt.postgresql.org/pub/repos/apt/ACCC4CF8.asc"})
     (act/package-manager :update)
     (act/package "postgresql")
     )}))

(def unit-load
  (api/group-spec
   "unit-load"
   :extends [base-server unit-load-server]
   :node-spec default-node-spec
   :count 1))
