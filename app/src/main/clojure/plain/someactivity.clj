(ns plain.someactivity
  (:import
            (android.support.v7.app AppCompatActivity)
            (com.example.ndksample.myapplication.R$layout))
  (:gen-class
    :name "plain.someactivity.MyActivity"
    :exposes-methods {onCreate superOnCreate}
     :extends android.support.v7.app.AppCompatActivity
     :prefix "some-")
    )

(defn some-onCreate [this bundle]
    (.superOnCreate this bundle)
    (.setContentView this com.example.ndksample.myapplication.R$layout/activity_main)
    )
