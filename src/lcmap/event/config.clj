(ns lcmap.event.config
  ""
  (:require [lcmap.config.helpers :refer :all]
            [lcmap.logger.config :as logger-cfg]
            [schema.core :as schema]))

(def opt-spec [])

(def event-schema
  {:lcmap.event {:host schema/Str
                 :port schema/Num
                 schema/Keyword schema/Str}})

(def cfg-schema
  (merge event-schema
         logger-cfg/logger-schema
         {schema/Keyword schema/Any}))

(def defaults
  {:ini *lcmap-config-ini*
   :args *command-line-args*
   :spec opt-spec
   :schema cfg-schema})
