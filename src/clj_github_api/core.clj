;; A simple clojure app which gets the top 10 contributors of a github repository.
(ns clj-github-api.core
  (require
   [clj-http.client :as http]
   [clj-json.core :as json]
   [clojure.pprint :refer [print-table]]))

;; Creates a map for the contibutor
(defn make-contrib
  [contrib]
  {:user-name  (get-in contrib ["author" "login"])
   :num-commits (get contrib "total")})

;; Sends an http request to a url
(defn http-request
  [url]
  (http/get url))

;; Adds the rand to the contib map
(defn add-rank
  [index {:keys [user-name num-commits]}]
  {:rank (inc index)
   :user-name user-name
   :num-commits num-commits})

;; Gets the top contributors for a repository
(defn get-top-contribs
  [repo]
  (let
   [url (format "https://api.github.com/repos/%s/stats/contributors" repo)
    response (http/get url)
    contribs (json/parse-string (:body (http-request url)))]

    (->> contribs
         (map make-contrib)
         (sort-by :num-commits)
         (reverse)
         (map-indexed add-rank)
         (take 10))))

;; Main
(def -main (comp print-table get-top-contribs))