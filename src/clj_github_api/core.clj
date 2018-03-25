;; A simple clojure app which gets the top 10 contributors of a github repository.
;; Author: Jordan Chalupka
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

(defn add-user-url
  [contrib]
  (assoc contrib :user-url (format "https://github.com/%s/", (:user-name contrib))))

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
         (map add-user-url)
         (take 10))))

(defn get-repo-url
  [url]
  (format "https://github.com/%s/", url))

(defn run
  [repo]
  (do
    (print (format "Getting Top 10 Contibutors for '%s' (%s)" repo (get-repo-url repo)))
    ((comp print-table get-top-contribs) repo)))

;; Main
(def -main run)