(ns neko.log
  "Utility for logging in Android. There are five logging macros: `i`,
  `d`, `e`, `v`, `w`; for different purposes. Each of them takes
  variable number of arguments and optional keyword arguments at the
  end: `:exception` and `:tag`. If `:tag` is not provided, current
  namespace is used instead. Examples:

    (require '[neko.log :as log])

    (log/d \"Some log string\" {:foo 1, :bar 2})
    (log/i \"Logging to custom tag\" [1 2 3] :tag \"custom\")
    (log/e \"Something went wrong\" [1 2 3] :exception ex)"
  {:author "Adam Clements"}
  (:import android.util.Log))

(defn- logger [logfn priority-kw args]
  (when-not ((set (:neko.init/ignore-log-priority *compiler-options*))
             priority-kw)
    (let [[strings kwargs] (split-with (complement #{:exception :tag}) args)
          {:keys [exception tag]} (if (odd? (count kwargs))
                                    (butlast kwargs)
                                    kwargs)
          tag (or tag (str *ns*))
          ex-form (if exception [exception] ())]
      `(binding [*print-readably* nil]
         (. Log ~logfn ~tag (pr-str ~@strings) ~@ex-form)))))

(defmacro e
  "Log an ERROR message, applying pr-str to all the arguments and taking
   an optional keyword :exception or :tag at the end which will print the
   exception stacktrace or override the TAG respectively"
  [& args] (logger 'e :error args))

(defmacro d
  "Log a DEBUG message, applying pr-str to all the arguments and taking
   an optional keyword :exception or :tag at the end which will print the
   exception stacktrace or override the TAG respectively"
  [& args] (logger 'd :debug args))

(defmacro i
  "Log an INFO message, applying pr-str to all the arguments and taking
   an optional keyword :exception or :tag at the end which will print the
   exception stacktrace or override the TAG respectively"
  [& args] (logger 'i :info args))

(defmacro v
  "Log a VERBOSE message, applying pr-str to all the arguments and taking
   an optional keyword :exception or :tag at the end which will print the
   exception stacktrace or override the TAG respectively"
  [& args] (logger 'v :verbose args))

(defmacro w
  "Log a WARN message, applying pr-str to all the arguments and taking
   an optional keyword :exception or :tag at the end which will print the
   exception stacktrace or override the TAG respectively"
  [& args] (logger 'w :warn args))
