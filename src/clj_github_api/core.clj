(ns clj-github-api.core
  (require
   [clj-http.client :as http]
   [clj-json.core :as json]))

(spit "output.tmp" (let [url "https://api.github.com/repos/rails/rails/stats/contributors"]
  (first (json/parse-string (:body (http/get url))))))

