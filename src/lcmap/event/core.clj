(ns lcmap.event.core
  "Code interface for using the event component"
  (:require [lcmap.event.definitions :as evt-defs]))

(defn exists? [event]
  "Determines if an event is defined"
  (contains? evt-defs/events event))

(defn valid? [event args]
  "Determine if an event+args is valid"
  (println "valid! called"))

(defn list-events []
  "List all defined events in the system"
  (println "listing events..."))

(defn event-args [event]
  "Return required arguments for a named event"
  (println "event-args called"))

(defn publish! [event args]
  "Sends an event"
  (println "publish! called"))

(defn subscribe! [event callback]
  "Create an event subscription"
  (println "subscribe! called"))

(defn unsubscribe! [event subscriber_id]
  "Remove an event subscription"
    (println "unsubscribe! called"))

(defn rpc! [service args]
    "Issues an rpc call to the named service"
    (println "rpc! called"))
