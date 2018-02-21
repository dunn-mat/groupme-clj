(ns groupme-clj.messages
  (:require [groupme-clj.utils :as util]))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;; Messages ;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn get-messages
  "Retrieves messages in the given group"
  [token group-id]
  (let [request (util/build-request (str "/groups/" group-id "/messages") token)
        resp (util/make-request request)]
    (util/extract-content resp)))

(defn create-message
  "create a message within the given group with the provided text"
  [token group-id text]
  (let [request (util/build-request (str "/groups/" group-id "/messages") token)
        body {"message" {"text" text, "source_guid" (str (System/currentTimeMillis))}}
        resp (util/make-request request "POST" body)]
    (util/extract-content resp)))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;; Likes ;;;;;;;;;;;;;;;;;;;;;;;
;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

(defn like-message
  "Like the given message in the given conversation"
  [token conversation-id message-id]
  (let [request (util/build-request (str "/messages/" conversation-id
                                         "/" message-id "/like")
                                    token)
        resp (util/make-request request "POST")]
    (if (= (:status resp) 200)
      (print (str "Successly liked message!")))
    (:status resp)))

(defn unlike-message
  "Unlike the given message in the given conversation"
  [token conversation-id message-id]
  (let [request (util/build-request (str "/messages/" conversation-id
                                         "/" message-id "/unlike")
                                    token)
        resp (util/make-request request "POST")]
    (if (= (:status resp) 200)
      (print (str "Successly unliked message!")))
    (:status resp)))