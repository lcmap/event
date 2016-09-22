
(ns lcmap.event.definitions
  "Event definitions for the LCMAP system.

  All information necessary to define, subscribe to and emit events is contained
  in definitions.clj.  This creates a data-driven approach to realizing the
  event component which minimizes code.

  Routing, exchange and binding information is captured here and
  tied directly to the events as they are defined."

  (:require [clojure.tools.logging :as log]
            [lcmap.event.exceptions :as exceptions]
            [lcmap.commons.time :as time]
            [schema.core :as s]))

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

(def base-schema {:type s/Str
                  :id s/Uuid
                  :related-to s/Uuid
                  :timestamp s/Regex})

;; For each event defined, any key with a value of nil is considered to be
;; a required argument.  A key with a value is considered a default.
;; TODO: replace with a data validation lib
(def schema
  {"data-missing" {:header {} :transport {} :schema {}}
   "new-input-detected" {:input-uri nil
                         :somedefault "defaulted"
                         :transport {:rabbitmq {}}}
   "ingest-campaign-started" {:transport {} :schema {}}
   "ingest-campaign-completed" {:transport {} :schema {}}
   "ingest-campaign-failed" {:transport {} :schema {}}
   "ingest-started" {:ucid nil
                     :input-uri nil}
   "ingest-completed" {:transport {} :schema {}}
   "model-execution-started" {:transport {} :schema {}}
   "model-execution-completed" {:transport {} :schema {}}
   "model-execution-failed" {:transport {} :schema {}}
   "inventory-added" {:transport {} :schema {}}
   "inventory-removed" {:transport {} :schema {}}})

(defn list-defs
  []
  (base-schema))

(defn evt-exists?
  "Is the event defined?"
  [event]
  (contains? list-defs event))

(defn get-def
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

(defn valid?
  "Is an event structured properly?"
  [evt]
  (true))
