(ns ^{:doc
  "Top-level LCMAP Event system component

  For more information, see the module-level code comments in
  ``lcmap.event.components``."}
  lcmap.event.components.system
  (:require [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]))

(defrecord LCMAPEventSystem []
  component/Lifecycle

  (start [component]
    (log/info "LCMAP Event system dependencies started; finishing LCMAP Event startup ...")
    ;; XXX add any start-up needed for system as a whole
    (log/debug "LCMAP Event System startup complete.")
    component)

  (stop [component]
    (log/info "Shutting down top-level LCMAP Event system ...")
    ;; XXX add any tear-down needed for system as a whole
    (log/debug "Top-level shutdown complete; shutting down system dependencies ...")
    component))

(defn new-lcmap-event-toplevel []
  (->LCMAPEventSystem))
