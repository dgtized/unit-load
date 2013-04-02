(defproject unit-load "0.1.0-SNAPSHOT"
  :description "Pallet infrastructure for a Clojure website"
  :url "https://github.com/dgtized/unit-load"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [com.palletops/pallet "0.8.0-beta.5"]
                 [org.cloudhoist/pallet-vmfest "0.3.0-alpha.2"]
                 [org.clojars.tbatchelli/vboxjws "4.2.6"]
                 [ch.qos.logback/logback-classic "1.0.11"]

                 [com.palletops/java-crate "0.8.0-beta.2"]]
  :repositories {"sonatype"
                 "http://oss.sonatype.org/content/repositories/releases"}
  :plugins [[com.palletops/pallet-lein "0.6.0-beta.7"]]
  :profiles
  {:pallet
   {:jvm-opts ["-Dvbox.home=/usr/lib/virtualbox"]}})
