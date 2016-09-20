(ns lcmap.event.core
  "Code interface for using the event component"
  (:require [lcmap.event.definitions :as evt-defs]))

(defn exists?
  "Determines if an event is defined"
  [event]
  (contains? evt-defs/events event))

(defn valid?
  "Determine if an event+args is valid"
  [event args]
  (println "valid! called"))

(defn list-events
  "List all defined events in the system"
  []
  (keys evt-defs/events))

(defn event-args
  "Return required arguments for a named event"
  [event]
  (println "event-args called"))

(defn publish!
  "Sends an event"
  [event args]
  (println "publish! called"))

(defn subscribe!
  "Create an event subscription"
  [event callback]
  (println "subscribe! called"))

(defn unsubscribe!
  "Remove an event subscription"
  [event subscriber_id]
  (println "unsubscribe! called"))

(defn rpc!
  "Issues an rpc call to the named service"
  [service args]
  (println "rpc! called"))
