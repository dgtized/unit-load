(ns unit-load.groups.unit-load
  (:require
   [pallet.api :as api]
   [pallet.crate.automated-admin-user :refer [with-automated-admin-user]]
   pallet.crate.java
   [pallet.crate.etc-hosts :as etc-hosts]
   [pallet.actions :as act]
   unit-load.crates.postgresql
   unit-load.crates.nginx))

(def default-node-spec
  (api/node-spec
   :image {:os-family :debian}
   :hardware {:min-cores 1}))

(def unit-load-server
  (api/server-spec
   :extends [(pallet.crate.java/server-spec {})
             (unit-load.crates.postgresql/server-spec {})
             (unit-load.crates.nginx/server-spec {})]
   :phases
   {:bootstrap (api/plan-fn (etc-hosts/set-hostname))}))

(def unit-load
  (api/group-spec
   "unit-load"
   :extends [with-automated-admin-user unit-load-server]
   :node-spec default-node-spec
   :count 1))
