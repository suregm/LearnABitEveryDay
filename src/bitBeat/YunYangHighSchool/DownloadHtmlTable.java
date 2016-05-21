package bitBeat.YunYangHighSchool;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by sure GM on 2016/5/21 20:12.
 */
public class DownloadHtmlTable {
	static String baseUrl = "http://www.yyhs.net/UserList.asp?ChannelID=0&OrderType=1&page=#";
	static int start = 1;
	static int end = 172;

	static List<Record> records = new ArrayList<Record>();

	public static void main(String[] args) throws IOException
	{
		for (int i = start; i <= end; i++)
		{
			String url = baseUrl.replace("#", i + "");

			Document doc = Jsoup.connect(url).get();
			Elements els = doc.select("table.Channel_border tr");
			for (int j = 3; j < els.size(); j++)
			{
				Element el = els.get(j);
				Elements tds = el.select("td");

				Record record = new Record(getText(tds, 0), getText(tds, 1), getText(tds, 2), getText(tds, 3), getText(tds, 4), getText(tds, 5), getText(tds, 6), getText(tds, 7));
				records.add(record);
			}

			System.out.println("处理第：" + i + "页");

		}

		FileSaver.save(records);
	}

	public static String readHtml(String myurl)
	{
		StringBuffer sb = new StringBuffer("");
		URL url;
		try
		{
			url = new URL(myurl);
			BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "gb2312"));
			String s = "";
			while ((s = br.readLine()) != null)
			{
				sb.append(s + "\r\n");
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}

	public static String getText(Elements el, int index)
	{
		String ret = "";
		String text = el.get(index).text();
		if (null == text || text.trim().equals(""))
		{
			ret = "";
		}
		else
		{
			ret = text;
		}
		return ret;
	}
}
