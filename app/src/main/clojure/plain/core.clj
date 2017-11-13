
(ns plain.core)

(gen-class
             :name core.plain.Demo
    :state state
    :init init
    :prefix "-"
    :main false
    ;; declare only new methods, not superclass methods
    :methods [[setLocation [String] void]
                          [getLocation [] String]])

;; when we are created we can set defaults if we want.
(defn -init []
        "store our fields as a hash"
        [[] (atom {:location "default"})])

;; little functions to safely set the fields.
(defn setfield
        [this key value]
        (swap! (.state this) into {key value}))

(defn getfield
        [this key]
        (@(.state this) key))

;; "this" is just a parameter, not a keyword
(defn -setLocation [this loc]
        (setfield this :location loc))

(defn  -getLocation
         [this]
         (getfield this :location))
