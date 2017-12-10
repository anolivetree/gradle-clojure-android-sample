(ns plain.someactivity2
  (:import
    (android.support.v7.app AppCompatActivity)
    (android.util Log)
    (com.example.ndksample.myapplication.R$id)
    (com.example.ndksample.myapplication.R$layout)
    (android.os Handler))
  (:require [org.httpkit.client :as http])

  (:gen-class
    :name "plain.someactivity2.MyActivity"
    :exposes-methods {onCreate superOnCreate}
    :extends android.support.v7.app.AppCompatActivity
    :prefix "some-")
  )

(defn fetch [url]
  (http/get url))

(defn some-onCreate [^plain.someactivity2.MyActivity this ^android.os.Bundle bundle]
  (.superOnCreate this bundle)
  (.setContentView this com.example.ndksample.myapplication.R$layout/activity_main)

  (.. this
      (findViewById com.example.ndksample.myapplication.R$id/getButton)
      (setOnClickListener (reify android.view.View$OnClickListener
                            (onClick [this v]
                              (Log/i "clojure" "hello")))))

  (let [tv (.findViewById this com.example.ndksample.myapplication.R$id/text)
        handler (Handler.)]
    (.start (Thread. (fn []
                       (let [data (:body @(fetch "http://www.yahoo.co.jp"))]
                         (.post handler #(.setText tv data))))))))
