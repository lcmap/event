(ns lcmap.event.config
  (:require [lcmap.config.helpers :refer :all]
            [schema.core :as schema]))

(def opt-spec [])

(def msg-cfg-schema
  {:host schema/Str})

(def cfg-schema
  {:lcmap.event msg-cfg-schema
   schema/Keyword schema/Any})

(def defaults
  {:ini *lcmap-config-ini*
   :args *command-line-args*
   :spec opt-spec
   :schema cfg-schema})
