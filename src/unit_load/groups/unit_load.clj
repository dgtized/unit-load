(ns unit-load.groups.unit-load
  (:require
   [pallet.api :as api]
   [pallet.crate.automated-admin-user :refer [automated-admin-user]]))

(def default-node-spec
  (api/node-spec
   :image {:os-family :debian}
   :hardware {:min-cores 1}))

(def base-server
  (api/server-spec
   :phases
   {:bootstrap (api/plan-fn (automated-admin-user))}))

(def unit-load-server
  (api/server-spec
   :phases
   {:configure (api/plan-fn
                ;; Add your crate class here
                 )}))

(def unit-load
  (api/group-spec
   "unit-load"
   :extends [base-server unit-load-server]
   :node-spec default-node-spec
   :count 1))
