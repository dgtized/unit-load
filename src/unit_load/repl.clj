(ns unit-load.repl
  (:use pallet.repl)
  (:require pallet.core
            pallet.compute
            pallet.compute.vmfest
            pallet.crate
            [pallet.stevedore :as stevedore]
            [pallet.api :as api]
            [pallet.script.lib :as lib]
            [unit-load.groups.unit-load :as ul]))

;; (def vmfest-service (pallet.compute/instantiate-provider :vmfest))

(comment
  (pallet.core/converge ul/unit-load
                        :compute (pallet.compute/instantiate-provider :vmfest))
  (use-pallet)
  (pallet.core/lift
   (assoc-in ul/unit-load [:phases :test]
             (api/plan-fn
              (stevedore/script (~lib/os-version-name))))
   :compute (pallet.compute/instantiate-provider :vmfest)
   :phase test)
  )
