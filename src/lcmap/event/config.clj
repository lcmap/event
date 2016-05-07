(ns lcmap.event.config
  (:require [lcmap.config.helpers :as cfg]
            [schema.core :as schema]))

(def opt-spec [])

(def db-cfg-schema
  {:hosts [schema/Str]})

(def msg-cfg-schema
  {:host schema/Str})

(def cfg-schema
  {:lcmap.event.components.db db-cfg-schema
   :lcmap.event.components.messaging msg-cfg-schema
   schema/Keyword schema/Any})

(def defaults
  {:ini (clojure.java.io/file (System/getenv "HOME") ".usgs" "lcmap.ini")
   :spec opt-spec
   :args *command-line-args*
   :schema cfg-schema})
