(ns lcmap.event.cli
  "Provide command-line interface to the event system."
  (:require [clojure.java.io :as io]
            [clojure.pprint :as pprint]
            [clojure.string :as string]
            [clojure.tools.cli :as cli]
            [clojure.tools.logging :as log]
            [com.stuartsierra.component :as component]
            [dire.core :refer [with-handler!]]
            [lcmap.config.helpers :as config-helpers]
            [lcmap.event.core :as core]
            [lcmap.event.components.system :as system]
            [lcmap.event.util :as util])

  (:gen-class))
