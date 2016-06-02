(defproject gov.usgs.eros/lcmap-event "0.5.0-SNAPSHOT"
  :description "LCMAP Event System"
  :url "https://github.com/USGS-EROS/lcmap-event"
  :license {:name "NASA Open Source Agreement, Version 1.3"
            :url "http://ti.arc.nasa.gov/opensource/nosa/"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/core.match "0.3.0-alpha4"]
                 [org.clojure/data.codec "0.1.0"]
                 [org.clojure/data.json "0.2.6"]
                 [org.clojure/data.xml "0.0.8"]
                 [org.clojure/core.memoize "0.5.8"]
                 ;; Componentization
                 [com.stuartsierra/component "0.3.0"]
                 ;; Logging and Error Handling -- note that we need to explicitly pull
                 ;; in a version of slf4j so that we don't get conflict messages on the
                 ;; console
                 [dire "0.5.3"]
                 [slingshot "0.12.2"]
                 ;; Messaging
                 [com.novemberain/langohr "3.5.0"]
                 ;; LCMAP Components
                 [gov.usgs.eros/lcmap-config "0.5.0-SNAPSHOT"]
                 [gov.usgs.eros/lcmap-logger "0.5.0-SNAPSHOT"]
                 [gov.usgs.eros/lcmap-client-clj "0.5.0-SNAPSHOT"]
                 ;; XXX note that we may still need to explicitly include the
                 ;; Apache Java HTTP client, since the version used by the LCMAP
                 ;; client is more recent than that used by Chas Emerick's
                 ;; 'friend' library (the conflict causes a compile error which
                 ;; is worked around by explicitly including Apache Java HTTP
                 ;; client library).
                 ;; XXX temp dependencies:
                 [org.apache.httpcomponents/httpclient "4.5"]
                 [clojure-ini "0.0.2"]
                 [clj-http "2.0.0"]
                 ;; Data types, encoding, etc.
                 [byte-streams "0.2.0"]
                 [clj-time "0.11.0"]
                 [commons-codec "1.9"]
                 ;; Dev and project metadata
                 [leiningen-core "2.5.3"]]
  :plugins [[lein-ring "0.9.7"]
            [lein-pprint "1.1.1"]
            [lein-codox "0.9.1"]
            [lein-simpleton "1.3.0"]]
  :source-paths ["src" "test/support/auth-server/src"]
  :java-agents [[co.paralleluniverse/quasar-core "0.7.3"]]
  :jvm-opts ["-Dco.paralleluniverse.fibers.detectRunawayFibers=false"]
  :repl-options {:init-ns lcmap.event.dev}
  :main lcmap.event.app
  :target-path "target/%s"
  :codox {:project {:name "LCMAP Event System Library"
                    :description "The Event Subscription and Notification Component for the USGS Land Change Monitoring Assessment and Projection (LCMAP) Computation and Analysis Platform"}
          :namespaces [#"^lcmap.event\."]
          :output-path "docs/master/current"
          :doc-paths ["docs/source"]
          :metadata {:doc/format :markdown
                     :doc "Documentation forthcoming"}}
  ;; List the namespaces whose log levels we want to control; note that if we
  ;; add more dependencies that are chatty in the logs, we'll want to add them
  ;; here.
  :logging-namespaces [lcmap.event
                       lcmap.client]
  :profiles {
    ;; configuration for dev environment -- if you need to make local changes,
    ;; copy `:env { ... }` into `{:user ...}` in your ~/.lein/profiles.clj and
    ;; then override values there
    :dev {
      :dependencies [[org.clojure/tools.namespace "0.3.0-alpha3"]
                     [slamhound "1.5.5"]]
      :aliases {"slamhound" ["run" "-m" "slam.hound"]}
      :source-paths ["dev-resources/src"]
      :env
        {:active-profile "development"
         :messaging {:host "127.0.0.1"
                     :port 5672
                     :vhost "/"
                     :default-exchange-name "lcmap.event"
                     :default-queue-name "lcmap.event-stream"}}}
    :testing {
      :env {
        :log-level :info}}})
