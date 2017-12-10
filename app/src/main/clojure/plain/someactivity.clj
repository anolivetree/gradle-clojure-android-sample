(ns plain.someactivity
  (:import
            (android.support.v7.app AppCompatActivity)
            (android.util Log)
            (com.example.ndksample.myapplication.R$layout))
            (:require [org.httpkit.client :as http])

  (:gen-class
    :name "plain.someactivity.MyActivity"
    :exposes-methods {onCreate superOnCreate}
     :extends android.support.v7.app.AppCompatActivity
     :prefix "some-")
    )

(defn fetch [url]
  (http/get url))

(defn some-onCreate [^plain.someactivity.MyActivity this ^android.os.Bundle bundle]
    (.superOnCreate this bundle)
    (.setContentView this com.example.ndksample.myapplication.R$layout/activity_main)

  (.start (Thread. (fn []
            (let [data (fetch "http://www.yahoo.co.jp")]
                              (Log/i "clojure" (:body @data))))

              ))

    )
