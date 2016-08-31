(defproject gov.usgs.eros/lcmap-event "1.0.0-SNAPSHOT"
  :parent-project {
    :coords [gov.usgs.eros/lcmap-system "1.0.0-SNAPSHOT"]
    :inherit [
      :deploy-repositories
      :license
      :managed-dependencies
      :plugins
      :pom-addition
      :repositories
      :target-path
      ;; XXX The following can be un-commented once this issue is resolved:
      ;;     * https://github.com/achin/lein-parent/issues/3
      ;; [:profiles [:uberjar :dev]]
      ]}
  :description "LCMAP Event System"
  :url "https://github.com/USGS-EROS/lcmap-event"
  :dependencies [[org.clojure/clojure]
                 [org.clojure/core.match]
                 [org.clojure/data.codec]
                 [org.clojure/data.json]
                 [org.clojure/data.xml]
                 [org.clojure/core.memoize]
                 ;; Componentization
                 [com.stuartsierra/component]
                 ;; Logging and Error Handling -- note that we need to explicitly pull
                 ;; in a version of slf4j so that we don't get conflict messages on the
                 ;; console
                 [dire]
                 [slingshot]
                 ;; Messaging
                 [com.novemberain/langohr]
                 ;; LCMAP Components
                 [gov.usgs.eros/lcmap-config]
                 [gov.usgs.eros/lcmap-logger]
                 [gov.usgs.eros/lcmap-client-clj]
                 ;; XXX note that we may still need to explicitly include the
                 ;; Apache Java HTTP client, since the version used by the LCMAP
                 ;; client is more recent than that used by Chas Emerick's
                 ;; 'friend' library (the conflict causes a compile error which
                 ;; is worked around by explicitly including Apache Java HTTP
                 ;; client library).
                 ;; XXX temp dependencies:
                 [org.apache.httpcomponents/httpclient]
                 [clojure-ini]
                 [clj-http]
                 ;; Data types, encoding, etc.
                 [byte-streams]
                 [clj-time]
                 [commons-codec]
                 ;; Dev and project metadata
                 [leiningen-core]]
  :plugins [[lein-parent "0.3.0"]]
  :source-paths ["src"]
  :java-agents [[co.paralleluniverse/quasar-core "0.7.3"]]
  :jvm-opts ["-Dco.paralleluniverse.fibers.detectRunawayFibers=false"]
  :repl-options {:init-ns lcmap.event.dev}
  :main lcmap.event.app
  :codox {:project {:name "lcmap.event"
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
    :uberjar {:aot :all}
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
                     :vhost "/lcmap"
                     :default-exchange-name "lcmap.event"
                     :default-queue-name "lcmap.event-stream"}}}
    :testing {
      :env {
        :log-level :info}}})
