
(ns lcmap.event.definitions
  "Event definitions for the LCMAP system.

  All information necessary to define, subscribe to and emit events is contained
  in definitions.clj.  This creates a data-driven approach to realizing the
  event component which minimizes code.

  Routing, exchange and binding information is captured here and
  tied directly to the events as they are defined."

  (:require [clojure.tools.logging :as log]
            [clj-time.core :as time]
            [lcmap.event.exceptions :as exceptions]))

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

(def base-event {:timestamp nil})

   ;; For each event defined, any key with a value of nil is considered to be
   ;; a required argument.  A key with a value is considered a default
(def events
  {"data-missing" {}
   "new-input-detected" {:input-uri nil
                         :somedefault "defaulted"
                         :transport {:rabbitmq {}}}
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

(defn evt-exists?
  "Is the event defined?"
  [event]
  (contains? events event))

(defn get-evt
  "Returns the event definition"
  [event]
  (if (evt-exists? event)
    (merge base-event (get events event))
    nil))

(defn attr-exists?
  "Is the event attribute defined?"
  [event attribute]
  (and (evt-exists? event) (contains? (get-evt event) attribute)))

(defn attr-reqd?
  "Is an event attribute required?"
  [event attribute]
  (if (attr-exists? event attribute)
    (nil? (get (get-evt event) attribute))
    nil))

(defn attr-opt?
  "Is an event attribute optional?"
  [event attribute]
  (not (attr-reqd? event attribute)))

;; (defn reqd-attrs
;; "Returns all attributes that must be supplied for an event"
;; [event]
;; (println "reqd-attrs")
