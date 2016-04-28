(ns lcmap.event.exceptions)

(defn error [type msg]
  (ex-info (str type) {:msg msg :type type}))

(defn lcmap-error [msg]
  (error 'LCMAP-Error msg))

(defn auth-error [msg]
  (error 'Auth-Error msg))
