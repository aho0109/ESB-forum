use ESB;

CREATE PROCEDURE GetPostsOrderByLastActivityDesc
AS
BEGIN

SELECT p.* 
FROM Post p
LEFT JOIN (
			SELECT POSTID, MAX(LastActivityTime) AS LastActivityTime 
			FROM (
					SELECT POSTID, MAX(CREATEDAT) AS LastActivityTime FROM Replies GROUP BY POSTID
					UNION ALL
					SELECT POSTID, MAX(CREATEDAT) AS LastActivityTime FROM Comment GROUP BY POSTID
					) AS Activity
					GROUP BY POSTID
			) AS Activity ON p.POSTID = Activity.POSTID 
ORDER BY COALESCE(Activity.LastActivityTime, p.CREATEDAT) DESC

END;
GO

---------------------------------------------------------------------------------------------------------------


CREATE PROCEDURE FindByKeywords
	@keyword NVARCHAR(100)
AS
BEGIN

SELECT p.postID, p.postTitle, p.content,
           p.userID, p.sboardID,
           p.createdAt, p.isDeleted
    FROM Post p
    LEFT JOIN Replies r ON p.postID = r.postID
    WHERE (p.postTitle LIKE '%' + @keyword + '%' OR
           p.content LIKE '%' + @keyword + '%' OR
           r.replyContent LIKE '%' + @keyword + '%' OR
           r.replyContent LIKE '%' + @keyword + '%')
          AND p.isDeleted = 0
          AND (r.isDeleted = 0 OR r.isDeleted IS NULL);

END;
GO

---------------------------------------------------------------------------------------------------------------


drop PROCEDURE GetPostsOrderByLastActivityDesc
drop PROCEDURE FindByKeywords