(defproject cjxenon "0.1.1"
  :description "A xenon client."
  :url "https://github.com/toliaqat/cjxenon"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main cjxenon.core-test
  :dependencies [
                 [org.clojure/tools.logging "0.2.6"]
                 [org.clojure/clojure "1.8.0"]
                 [org.clojure/data.json "0.2.6"]
                 [cheshire "5.6.3"]
                 [clj-http "2.2.0"]]
  :profiles {:dev {:dependencies [[org.clojure/clojure "1.8.0"]]}})
