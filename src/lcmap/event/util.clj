(ns lcmap.event.util)

(defn add-shutdown-handler [func]
  (.addShutdownHook (Runtime/getRuntime)
                    (Thread. func)))

(defn in?
  "This function returns true if the provided sequence contains the given
  element."
  [seq elm]
  (some #(= elm %) seq))
