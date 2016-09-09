(ns lcmap.event.definitions
  "Event definitions for the LCMAP system.

  All information necessary to define, subscribe to and emit events is contained
  in definitions.clj.  This creates a data-driven approach to realizing the
  event component which should minimize code.

  Routing, exchange and binding information should also be captured here and
  tied directly to the events as they are defined (at least for now)."

  (:require [clojure.tools.logging :as log]
            [clj-time.core :as time]))

(def default-headers
  "The following headers are defined by AMQP 0.9.1
  - Content type
  - Content encoding
  - Routing key
  - Delivery mode (persistent or not)
  - Message priority
  - Message publishing timestamp
  - Expiration period
  - Publisher application id"
  {:content-type "application/json"
   :content-encoding "utf-8"
   :routing-key nil
   :delivery-mode "persistent"
   :message-priority "normal"
   :when nil
   :expires nil
   :publisher nil})

;; For each event defined, any key with a value of nil is considered to be
;; a required argument
(def events
  {"base-event" {}
   "new-input-detected" {:input-uri nil}
   "ingest-campaign-started" {}
   "ingest-campaign-completed" {}
   "ingest-campaign-failed" {}
   "ingest-started" {:ucid nil
                     :input-uri nil}
   "ingest-completed" {}
   "model-execution-started" {}
   "model-execution-completed" {}
   "model-execution-failed" {}
   "inventory-added" {}
   "inventory-removed" {}})

(defn exists? [event]
  "Determines if an event is defined"
  (contains? events event))

(defn valid? [event args]
  "Determine if an event+args is valid"
  ())

(defn event-args [event]
  "Return required arguments for a named event"
  ())

(defn fire-event! [event args]
  "Return a new event" ())

(defn subscribe! [event callback]
  "Create an event subscription" ())

(defn unsubscribe! [event callback]
  "Remove an event subscription" ())
