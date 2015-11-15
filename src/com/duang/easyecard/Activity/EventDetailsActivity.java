package com.duang.easyecard.Activity;

import java.util.StringTokenizer;

import com.duang.easyecard.R;
import com.duang.easyecard.db.MyDatabaseHelper;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class EventDetailsActivity extends BaseActivity{
	
	private MyDatabaseHelper dbHelper;
	
	//private ImageView imgvUserImg;
	
	private TextView tvStu_id;
	private TextView tvName;
	private TextView tvContact;
	private TextView tvEventTimeTitle;
	private TextView tvEventTime;
	private TextView tvEventPlaceTitle;
	private TextView tvEventPlace;
	private TextView tvEventDescription;
	private TextView tvEventPublisher;
	private TextView tvEventAddTime;
	private TextView tvEventState;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.event_details);
		
		//�ؼ��ĳ�ʼ�� Ҫ�ڼ��ز����ļ�֮��
		//imgvUserImg = (ImageView) findViewById(R.id.event_details_img);
		
		tvStu_id = (TextView) findViewById(R.id.event_details_stu_id);
		tvName = (TextView) findViewById(R.id.event_details_name);
		tvContact = (TextView) findViewById(R.id.event_details_contact);
		tvEventTimeTitle = (TextView) findViewById(R.id.event_details_event_time_title);
		tvEventTime = (TextView) findViewById(R.id.event_details_event_time);
		tvEventPlaceTitle = (TextView) findViewById(R.id.event_details_event_place_title);
		tvEventPlace = (TextView) findViewById(R.id.event_details_event_place);
		tvEventDescription = (TextView) findViewById(R.id.event_details_event_description);
		tvEventPublisher = (TextView) findViewById(R.id.event_details_event_publisher);
		tvEventAddTime = (TextView) findViewById(R.id.event_details_event_add_time);
		tvEventState = (TextView) findViewById(R.id.event_details_event_state);
		
		//�򿪻򴴽����ݿ�
		dbHelper = new MyDatabaseHelper(this, "EasyEcard.db", null, 1);
		if (dbHelper == null)
		{
			Log.e("dbHelper", "Fail to open database.");
		}		
		
		Intent intent = getIntent();
		String data = intent.getStringExtra("extra_data");
		Log.d("extra_data in EventDetailsActivity", data);
		
		String split = "_";
		StringTokenizer token = new StringTokenizer(data, split);
		String stu_id = token.nextToken();
		//Log.d("EventDetailsActivity stu_id in data", stu_id);
		
		int FLAG = Integer.parseInt(token.nextToken());
		Log.d("EventDetailsActivity FLAG in data", String.valueOf(FLAG));
		
		tvStu_id.setText(stu_id);	//��ʾѧ��
		
		//�ж�FLAG��ֵ��ѡ��ͬ�ı����в�ѯ
		switch (FLAG)
		{
			case 3:
			case 1:
				getInfoFromLostEvent(stu_id);
				break;
			case 2:
				getInfoFromFoundEvent(stu_id);
				break;
			default:
		}
		
	}

	protected void getInfoFromLostEvent(String stu_id) {
		Log.d("stu_id in getInfoFromLostEvent", stu_id);
		//Log.d("dbHelper in getInfoFromLostEvent", dbHelper.);
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("LostEvent", null, null, null, null, null, null);
		
		if (cursor.moveToLast())
		{
			do{
				if (stu_id.equals(cursor.getString(cursor.getColumnIndex("owner_stu_id"))))
				{
					//ʧ������
					tvName.setText(cursor.getString(cursor.getColumnIndex("owner_name")));
					//ʧ����ϵ��ʽ
					tvContact.setText(cursor.getString(cursor.getColumnIndex("owner_contact")));
					//��ʾ��ʧʱ�䣬��ʽΪ��2015��11��14��22��54�֡�
					tvEventTimeTitle.setText("��ʧʱ��");
					String date = cursor.getString(cursor.getColumnIndex("lost_date"));
					String split = "-";
					StringTokenizer token = new StringTokenizer(date, split);
					String year = null, month = null, day = null;
					if (token.hasMoreTokens())
					{
						year = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						month = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						day = token.nextToken();
					}
					String time_in_day = cursor.getString(cursor.getColumnIndex("lost_time"));
					split = ":";
					token = new StringTokenizer(time_in_day, split);
					String hour = null, minute = null;
					if (token.hasMoreTokens())
					{
						hour = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						minute = token.nextToken();
					}
					tvEventTime.setText(year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					Log.d("EventLostTime", year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");

					//��ʾ��ʧ�ص�
					tvEventPlaceTitle.setText("��ʧ�ص�");
					tvEventPlace.setText(cursor.getString(cursor.getColumnIndex("lost_place")));
					//��ʾ����
					tvEventDescription.setText(cursor.getString(cursor.getColumnIndex("description")));
					//��ʾ�����ߣ���ѧ�ţ�
					tvEventPublisher.setText(cursor.getString(cursor.getColumnIndex("publisher_stu_id")));
					//��ʾ����ʱ��
					date = cursor.getString(cursor.getColumnIndex("lost_date"));
					split = "-";
					token = new StringTokenizer(date, split);
					year = null;
					month = null;
					day = null;
					if (token.hasMoreTokens())
					{
						year = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						month = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						day = token.nextToken();
					}
					time_in_day = cursor.getString(cursor.getColumnIndex("lost_time"));
					split = ":";
					token = new StringTokenizer(time_in_day, split);
					hour = null;
					minute = null;
					if (token.hasMoreTokens())
					{
						hour = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						minute = token.nextToken();
					}
					tvEventAddTime.setText(year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					Log.d("EventAddTime", year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					
					//��ʾ�¼�״̬  {"�Ѿ��ҵ�", "����Ѱ��"}
					if (cursor.getString(cursor.getColumnIndex("found_flag")).equals("0"))
					{
						Log.d("found_flag", cursor.getString(cursor.getColumnIndex("found_flag")));
						tvEventState.setText("����Ѱ��");
					}
					else
					{
						Log.d("found_flag", cursor.getString(cursor.getColumnIndex("found_flag")));
						tvEventState.setText("�Ѿ��ҵ�");
					}
				}
			} while (cursor.moveToPrevious());
		}
		cursor.close();
		db.close();
	}
	
	private void getInfoFromFoundEvent(String stu_id) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = dbHelper.getReadableDatabase();
		Cursor cursor = db.query("FoundEvent", null, null, null, null, null, null);
		if (cursor.moveToLast())
		{
			do{
				if (stu_id.equals(cursor.getString(cursor.getColumnIndex("owner_stu_id"))))
				{
					//ʧ������
					tvName.setText(cursor.getString(cursor.getColumnIndex("owner_name")));
					//ʰ������ϵ��ʽ
					tvContact.setText(cursor.getString(cursor.getColumnIndex("owner_contact")));
					//��ʾʰ��ʱ�䣬��ʽΪ��2015��11��14��22��54�֡�
					tvEventTimeTitle.setText("ʰ��ʱ��");
					String date = cursor.getString(cursor.getColumnIndex("found_date"));
					String split = "-";
					StringTokenizer token = new StringTokenizer(date, split);
					String year = null, month = null, day = null;
					if (token.hasMoreTokens())
					{
						year = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						month = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						day = token.nextToken();
					}

					String time_in_day = cursor.getString(cursor.getColumnIndex("found_time"));
					split = ":";
					token = new StringTokenizer(time_in_day, split);
					String hour = null;
					String minute = null;
					if (token.hasMoreTokens())
					{
						hour = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						minute = token.nextToken();
					}
					tvEventTime.setText(year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					Log.d("EventFoundTime", year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					
					//��ʾʰ��ص�
					tvEventPlaceTitle.setText("ʰ��ص�");
					tvEventPlace.setText(cursor.getString(cursor.getColumnIndex("found_place")));
					//��ʾ����
					tvEventDescription.setText(cursor.getString(cursor.getColumnIndex("description")));
					//��ʾ�����ߣ���ѧ�ţ�
					tvEventPublisher.setText(cursor.getString(cursor.getColumnIndex("publisher_stu_id")));
					//��ʾ����ʱ��
					date = cursor.getString(cursor.getColumnIndex("lost_date"));
					split = "-";
					token = new StringTokenizer(date, split);
					if (token.hasMoreTokens())
					{
						year = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						month = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						day = token.nextToken();
					}
					time_in_day = cursor.getString(cursor.getColumnIndex("lost_time"));
					split = ":";
					token = new StringTokenizer(time_in_day, split);
					hour = null;
					minute = null;
					if (token.hasMoreTokens())
					{
						hour = token.nextToken();
					}
					if (token.hasMoreTokens())
					{
						minute = token.nextToken();
					}
					tvEventAddTime.setText(year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					Log.d("EventAddTime", year + "��" + month + "��" + day + "��" + hour + "��" + minute + "��");
					
					//��ʾ�¼�״̬  {"�Ѿ��黹", "Ѱ��ʧ��"}
					if (cursor.getString(cursor.getColumnIndex("returned_flag")).equals("0"))
					{
						Log.d("returned_flag", cursor.getString(cursor.getColumnIndex("returned_flag")));
						tvEventState.setText("Ѱ��ʧ��");
					}
					else
					{
						Log.d("returned_flag", cursor.getString(cursor.getColumnIndex("returned_flag")));
						tvEventState.setText("�Ѿ��黹");
					}
				}
			} while (cursor.moveToPrevious());
		}
		cursor.close();
		db.close();
	}
	
}