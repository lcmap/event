(ns lcmap.event.core
  "Code interface for using the event component"
  (:require [lcmap.event.definitions :as evt-defs]))

(defn exists? [event]
  "Determines if an event is defined"
  (contains? evt-defs/events event))

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
