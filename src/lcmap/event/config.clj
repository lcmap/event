(ns lcmap.event.config
  ""
  (:require [lcmap.config.helpers :refer :all]
            [schema.core :as schema]))

(def opt-spec [])

(def event-schema
  {:lcmap.event {:host schema/Str
                 :port schema/Num
                 schema/Keyword schema/Str}})

(def logger-schema
  {:lcmap.logger {:level schema/Str
                  :namespaces [schema/Str]
                  :msg-host schema/Str
                  :msg-port schema/Num
                  schema/Keyword schema/Str}})

(def cfg-schema
  (merge event-schema
         logger-schema
         {schema/Keyword schema/Any}))

(def defaults
  {:ini *lcmap-config-ini*
   :args *command-line-args*
   :spec opt-spec
   :schema cfg-schema})
