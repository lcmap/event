(ns lcmap.event.definitions

  "Event definitions for the LCMAP system.

  This is the definitive source of truth for all event definitions within the
  LCMAP system.
  "

  (:require [clojure.tools.logging :as log]
            [clj-time.core :as time]))

(def base-event {:name nil
                 :timestamp 0
                 :src-component nil})

(defn new-input-detected
  "Fired when a new input has been observed and should be ingested"
    [input-uri]
    (merge base-event {:name "new-input-detected"
                       :input-uri input-uri})

 (defn ingest-started
    [input-id campaign-id]
    (merge base-event {:name "ingest-started"
                       :input-id input-id
                       :campaign-id campaign-id})))
