(ns om-datepicker.utils
  (:require [cljs-time.format :as timef]
            [cljs-time.core :as time]))

(defn el-data
  " Example:

  <div data-example=':something'></div>

  (el-data el :example)
  :something
  "
  [el key]
  (cljs.reader/read-string (.getAttribute el (str "data-" (name key)))))

(defn- get-hr-month
  "Returns a human readable month from a cljs-time date."
  [date]
  (timef/unparse (timef/formatter "MMMM") date))

(defn- get-date
  "Returns a human readable date from a cljs-time date."
  [date]
  (timef/unparse (timef/formatter "yyyy/MM/dd") (time/date-time date)))

(defn zero-pad
  "Given a 0 string will return a new string with 0
  paddings to the begining of the string.

  Example:
    (zero-pad '01' 1)
    '001'
  "
  [s len]
  (str (apply str (repeat (- len (count (str s)))  "0")) s))

(defn get-utc-formatted-date
  "Example:

  (get-date #inst '1990-06-09')
  '09/06/1990'
  "
  [date]
  (str (zero-pad (.getUTCDate date) 2) "/"
              (zero-pad (inc (.getUTCMonth date)) 2) "/"
              (.getUTCFullYear date)))
