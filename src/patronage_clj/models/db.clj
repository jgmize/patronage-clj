(ns patronage-clj.models.db
  (:use korma.core
        [korma.db :only (defdb)])
  (:require [patronage-clj.models.schema :as schema]))

(defdb db schema/db-spec)

(defentity users
  (has-many bids))

(defn create-user [user]
  (insert users
          (values user)))

(defn update-user [id first-name last-name email]
  (update users
  (set-fields {:first_name first-name
               :last_name last-name
               :email email})
  (where {:id id})))

(defn get-user [id]
  (first (select users
                 (where {:id id})
                 (limit 1))))

(defn get-all-users [] (select users))

(defentity bids
  (belongs-to users {:fk :user_id}))

(defn create-bid [bid]
  (insert bids
          (values bid)))

(defn update-bid [id url offer ask]
  (update users
          (set-fields {:url url
                       :offer offer
                       :ask ask})
          (where {:id id})))

(defn get-bid [id]
  (first (select bids
                 (where {:id id})
                 (limit 1))))

(defn get-all-bids [] (select bids))
