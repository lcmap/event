(ns lcmap.event.definitions)

(def base-event {:timestamp 0 :src-component nil})

(defn new-input-detected
    [input-src input-id]
    (merge base-event {:input-src input-src :input-id input-id}))
