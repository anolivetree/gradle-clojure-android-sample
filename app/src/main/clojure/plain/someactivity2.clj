(ns plain.someactivity2
  (:import
    (android.support.v7.app AppCompatActivity)
    (android.util Log)
    (com.example.ndksample.myapplication.R$layout))
    (:require [org.httpkit.client :as http])
  (:gen-class
    :name "plain.someactivity2.MyActivity"
    :exposes-methods {onCreate superOnCreate}
    :extends android.support.v7.app.AppCompatActivity
    :prefix "some-")
  )

(defn fetch [url]
  (http/get url))

(defn some-onCreate [this bundle]
  (.superOnCreate this bundle)
  (.setContentView this com.example.ndksample.myapplication.R$layout/activity_main)

  (.start (Thread.
            (let [data (fetch "http://www.yahoo.co.jp")]
              (Log/i "clojure" data))))
  )
