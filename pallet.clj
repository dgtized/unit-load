(require '[unit-load.groups.unit-load :refer [unit-load]])

(defproject unit-load
  :provider {:vmfest
             {:node-spec
              {:image {:os-family :debian
                       :os-64-bit true}}
              :selectors #{:default}}}
  :groups [unit-load])

