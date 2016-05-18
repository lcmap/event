(ns ^{:doc
  "Logger LCMAP Event system component

  Note that there are additional configuration settings in
  ``resources/logback.xml``. If we can figure out how to do this in code,
  it would be nice to have that done below with the rest of the logging setup.

  For more information, see the module-level code comments in
  ``lcmap.event.components``."}
  lcmap.event.components.logger
  (:require [clojure.tools.logging :as log]
            [clojure.tools.logging.impl :as log-impl]
            [com.stuartsierra.component :as component]
            [twig.core :as logger]))

(defn get-log-level
  "A convenience function for use in the Logger component."
  [component]
  (keyword (get-in component [:cfg :lcmap.logging :level])))

(defn get-namespaces
  "A convenience function for use in the Logger component."
  [component]
  (->> [:cfg :lcmap.logging :namespaces]
       (get-in component)
       (map symbol)
       (into [])))

(defrecord Logger []
  component/Lifecycle

  (start [component]
    (log/info "Setting up LCMAP Event system logging ...")
    (let [log-level (get-log-level component)
          namespaces (get-namespaces component)]
      (log/info "Using log-level" log-level)
      ;;(logger/set-level! namespaces log-level)
      ;;(dorun (map #(logger/set-level! % log-level) namespaces))
      (log/debug "Logging agent:" log/*logging-agent*)
      (log/debug "Logging factory:" (logger/get-factory))
      (log/debug "Logging factory name:" (logger/get-factory-name))
      (log/debug "Logger:" (logger/get-logger *ns*))
      (log/debug "Logger name:" (logger/get-logger-name *ns*))
      (log/debug "Logger level:" (logger/get-logger-level *ns*))
      (log/debug "Logger context:" (logger/get-logger-context *ns*))
      (log/debug "Logger configurator:" (logger/get-config *ns*))
      (log/debug "Set log level for these namespaces:" namespaces)
      (log/debug "Successfully configured logging.")
      component))

  (stop [component]
    (log/info "Tearing down LCMAP Event system logging ...")
    (log/debug "Component keys" (keys component))
    component))

(defn new-logger []
  (->Logger))
