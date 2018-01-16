package weibo4j.examples.comment;

import weibo4j.Comments;
import weibo4j.examples.oauth2.Log;
import weibo4j.model.Comment;
import weibo4j.model.WeiboException;
import weibo4j.util.WeiboConfig;

public class CreateComment {

	public static void main(String[] args) {
		String access_token = WeiboConfig.getValue("access_token");
		String comments = "你好";
		String id = "5605705908";
		Comments cm = new Comments(access_token);
		try {
			Comment comment = cm.createComment(comments, id);
			Log.logInfo(comment.toString());
		} catch (WeiboException e) {
			e.printStackTrace();
		}
	}
}
