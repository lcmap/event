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
    [input-src input-id]
    (merge base-event {:name "new-input-detected"
                       :input-src input-src
                       :input-id input-id})

 (defn ingest-started
    [input-src input-id]
    (merge base-event {:name "ingest-started"
                       :input-src input-src
                       :input-id input-id})))
