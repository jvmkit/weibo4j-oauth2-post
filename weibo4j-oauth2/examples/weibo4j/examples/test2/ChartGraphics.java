package weibo4j.examples.test2;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.imageio.ImageIO;

import weibo4j.util.WeiboConfig;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {
	private BufferedImage image;
	private int imageWidth = 180; // 图片的宽度
	private int imageHeight = 230; // 图片的高度

	// 生成图片文件
	@SuppressWarnings("restriction")
	public void createImage(String fileLocation) {
		BufferedOutputStream bos = null;
		if (image != null) {
			try {
				ImageIO.write(image, "png", new File(fileLocation));
				/*
				 * FileOutputStream fos = new FileOutputStream(fileLocation);
				 * bos = new BufferedOutputStream(fos);
				 * 
				 * JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
				 * encoder.encode(image); bos.close();
				 */
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bos != null) {// 关闭输出流
					try {
						bos.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

	public void graphicsGeneration(String ours, String num, String days,
			String imgurl) {
		int H_title = 0; // 头部高度
		int H_mainPic = 180; // 轮播广告高度
		int H_tip = 60; // 上网提示框高度
		int H_btn = 25; // 按钮栏的高度
		int tip_2_top = (H_title + H_mainPic);
		int btns_2_top = tip_2_top + H_tip + 20;
		int btn1_2_top = btns_2_top + 10;
		int btn2_2_top = btn1_2_top + H_btn;
		int shops_2_top = btn2_2_top + H_btn + 20;
		int W_btn = 280; // 按钮栏的宽度

		image = new BufferedImage(imageWidth, imageHeight,
				BufferedImage.TYPE_INT_RGB);
		// 设置图片的背景色
		Graphics2D g2d = image.createGraphics();
		image = g2d.getDeviceConfiguration().createCompatibleImage(imageWidth,
				imageHeight, Transparency.TRANSLUCENT);
		g2d.dispose();
		g2d = image.createGraphics();
		// main.setColor(Color.white);
		// main.fillRect(0, 0, imageWidth, imageHeight);

		/*
		 * //***********************页面头部 Graphics title =
		 * image.createGraphics(); //设置区域颜色 title.setColor(new Color(143, 0,
		 * 0)); //填充区域并确定区域大小位置 title.fillRect(0, 0, imageWidth, H_title);
		 * //设置字体颜色，先设置颜色，再填充内容 title.setColor(Color.white); //设置字体 Font
		 * titleFont = new Font("宋体", Font.BOLD, 14); title.setFont(titleFont);
		 * title.drawString("my head", 100, (H_title)/2+5);
		 */

		// ***********************插入中间广告图
		Graphics mainPic = image.getGraphics();
		BufferedImage bimg = null;
		try {
			bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (bimg != null) {
			mainPic.drawImage(bimg, 0, H_title, imageWidth, H_mainPic, null);
			mainPic.dispose();
		}
		// ***********************设置下面的提示框

		Graphics2D tip = image.createGraphics();
		// 设置区域颜色
		tip.setColor(new Color(70, 167, 234));
		// 填充区域并确定区域大小位置
		tip.fillRect(0, tip_2_top, imageWidth, H_tip);
		// 设置字体颜色，先设置颜色，再填充内容
		tip.setColor(Color.black);
		// 设置字体
		Font tipFont = new Font("楷体", Font.ITALIC, 20);
		tip.setFont(tipFont);
		Font tip2Font = new Font("楷体", Font.PLAIN, 30);
		tip.drawString(ours, 40, 200);
		tip.drawString(days, 130, 215);
		tip.setFont(tip2Font);
		tip.drawString(num, 85, 210);

		Font tip3Font = new Font("楷体", Font.ITALIC, 8);
		tip.setFont(tip3Font);
		tip.drawString("Love you Ran", 0, 228);
		// tip.drawString("正在返回商家主页", 100, tip_2_top+(H_tip)/2+10);

		// //***********************设置下面的按钮块
		// //设置字体颜色，先设置颜色，再填充内容
		// tip.setColor(Color.black);
		// tip.drawString("您可以选择的操作：", 20, btns_2_top);
		// tip.drawString("下面的小图标：", 20, shops_2_top);
		// //***********************按钮
		// Font btnFont = new Font("宋体", Font.BOLD, 14);
		// Graphics2D btn1 = image.createGraphics();
		// btn1.setColor(new Color(41,192 , 50));//#29C65A
		// btn1.fillRect(10, btn1_2_top, W_btn, H_btn);
		// btn1.setColor(Color.BLACK);
		// btn1.drawRect(10, btn1_2_top, W_btn, H_btn);
		// //btn1 文本
		// btn1.setColor(Color.white);
		// btn1.setFont(btnFont);
		// btn1.drawString("单击我啊", 120, btn1_2_top+(H_btn/2)+5);
		//
		// Graphics2D btn2 = image.createGraphics();
		// btn2.setColor(new Color(141,120 , 22));//#29C65A
		// btn2.fillRect(10, btn2_2_top, W_btn, H_btn);
		// btn2.setColor(Color.BLACK);
		// btn2.drawRect(10, btn2_2_top, W_btn, H_btn);
		// //btn2文本
		// btn2.setColor(Color.white);
		// btn2.setFont(btnFont);
		// btn2.drawString("单击我啊", 120, btn2_2_top+(H_btn/2)+5);

		createImage(WeiboConfig.getValue("upImgPath"));

	}

	public static void imgFactory(String n) throws ParseException {
		ChartGraphics cg = new ChartGraphics();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = sdf.parse("2015-05-22");
		Date date2 = new Date();
		Integer num = daysBetween(date, date2);
		try {
			String pathString = WeiboConfig.getValue("imgPath");
			cg.graphicsGeneration("Ours", String.valueOf(num), "days",
					WeiboConfig.getValue("imgPath") + n + ".png");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static int daysBetween(Date smdate, Date bdate)
			throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		smdate = sdf.parse(sdf.format(smdate));
		bdate = sdf.parse(sdf.format(bdate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(smdate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(bdate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

}