package com.jrgames;

import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;


import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import com.jrgames.Common.Common;

import com.jrgames.utils.CustomJsonRequest;
import com.jrgames.utils.LoadingBar;
import com.jrgames.utils.SessionMangement;

import static com.jrgames.Config.BaseUrl.URL_REGISTER;
import static com.jrgames.Config.Constants.KEY_ACCOUNNO;
import static com.jrgames.Config.Constants.KEY_ADDRESS;
import static com.jrgames.Config.Constants.KEY_BANK_NAME;
import static com.jrgames.Config.Constants.KEY_CITY;
import static com.jrgames.Config.Constants.KEY_DOB;
import static com.jrgames.Config.Constants.KEY_EMAIL;
import static com.jrgames.Config.Constants.KEY_HOLDER;
import static com.jrgames.Config.Constants.KEY_ID;
import static com.jrgames.Config.Constants.KEY_IFSC;
import static com.jrgames.Config.Constants.KEY_MOBILE;
import static com.jrgames.Config.Constants.KEY_PAYTM;
import static com.jrgames.Config.Constants.KEY_PHONEPAY;
import static com.jrgames.Config.Constants.KEY_PINCODE;
import static com.jrgames.Config.Constants.KEY_TEZ;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etPAddress,etPCity,etPPinCode,etAccNo,etBankName,etIfscCode,etAccHolderName,etPaytm,etTez,etPhonePay  ,et_email,et_mobile;
    Common common;
    SessionMangement sessionMangement;
    LoadingBar progressDialog;
    String wrong="Something Went Wrong";
    private TextView btn_back;
    TextView tv_profile,tv_address,tv_bank_detail,tv_account,et_dob;
    LinearLayout ll_profile,ll_address,ll_bank_detail,ll_account;
    int   year,month,day;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private Button btnDAddress,btnDBank,btnDPaytm,btnDGoogle,btnUpdatePass ,btnUpdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        common=new Common(this);
        sessionMangement = new SessionMangement(ProfileActivity.this);

        btn_back=(TextView)findViewById(R.id.txt_back);
        progressDialog=new LoadingBar(this);




        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        common.setSessionTimeOut(this);
        btnDAddress=(Button)findViewById(R.id.add_address);
        btnDAddress.setOnClickListener(this);
        btnUpdatePass = findViewById(R.id.btn_update_pass);
        etPAddress=(EditText)findViewById(R.id.etAddress);
        etPCity=(EditText)findViewById(R.id.etCity);
        etPPinCode=(EditText)findViewById(R.id.etPin);
        btnDBank=(Button)findViewById(R.id.add_bank);
        btnDBank.setOnClickListener(this);
        btnDPaytm = findViewById(R.id.add_paytm);
        btnDPaytm.setOnClickListener(this);
        etAccNo=(EditText)findViewById(R.id.etAccNo);
        etBankName=(EditText)findViewById(R.id.etBankName);
        etIfscCode=(EditText)findViewById(R.id.etIfsc);
        etAccHolderName=(EditText)findViewById(R.id.etHName);
        etPaytm=(EditText)findViewById(R.id.etPaytmNo);
        etTez=(EditText)findViewById(R.id.etTezNo);
        etPhonePay=(EditText)findViewById(R.id.etPhone);
        et_dob = findViewById(R.id.et_dob);
        et_dob.setOnClickListener(this);
        et_email = findViewById(R.id.et_email);
        et_mobile = findViewById(R.id.etMobile);




        tv_profile=findViewById(R.id.tv_profile);
        tv_address= findViewById(R.id.tv_address);
        tv_bank_detail=findViewById(R.id.tv_bank_detail);
        tv_account=findViewById(R.id.tv_account);

        ll_profile=findViewById(R.id.ll_profile);
        ll_address=findViewById(R.id.ll_address);
        ll_bank_detail=findViewById(R.id.ll_bank_detail);
        ll_account=findViewById(R.id.ll_account);




        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(this);

//        String ad= Prevalent.currentOnlineuser.getAddress().toString();
//        String ct=Prevalent.currentOnlineuser.getCity().toString();
//        String pn=Prevalent.currentOnlineuser.getPincode().toString();
//        String ac=Prevalent.currentOnlineuser.getAccountno().toString();
//        String bn=Prevalent.currentOnlineuser.getBank_name().toString();
//        String ic=Prevalent.currentOnlineuser.getIfsc_code().toString();
//        String ah=Prevalent.currentOnlineuser.getAccount_holder_name().toString();
//        String x=Prevalent.currentOnlineuser.getPhonepay_no().toString();
//        String tz=Prevalent.currentOnlineuser.getTez_no().toString();
//        String p=Prevalent.currentOnlineuser.getPaytm_no().toString();
//        String mobile = Prevalent.currentOnlineuser.getMobileno();
//        String email = Prevalent.currentOnlineuser.getEmail();
//        String dob = Prevalent.currentOnlineuser.getDob();


        String ad= sessionMangement.getUserDetails().get(KEY_ADDRESS).toString();
        String ct=sessionMangement.getUserDetails().get(KEY_CITY).toString();
        String pn=sessionMangement.getUserDetails().get(KEY_PINCODE).toString();
        String ac=sessionMangement.getUserDetails().get(KEY_ACCOUNNO).toString();
        String bn=sessionMangement.getUserDetails().get(KEY_BANK_NAME).toString();
        String ic=sessionMangement.getUserDetails().get(KEY_IFSC).toString();
        String ah=sessionMangement.getUserDetails().get(KEY_HOLDER).toString();
        String x=sessionMangement.getUserDetails().get(KEY_PHONEPAY).toString();
        String tz=sessionMangement.getUserDetails().get(KEY_TEZ).toString();
        String p=sessionMangement.getUserDetails().get(KEY_PAYTM).toString();
        String mobile = sessionMangement.getUserDetails().get(KEY_MOBILE);
        String email = sessionMangement.getUserDetails().get(KEY_EMAIL);
        String dob = sessionMangement.getUserDetails().get(KEY_DOB);


        et_email.setText(email);
        et_dob.setText(dob);
        et_mobile.setText(mobile);
        et_mobile.setEnabled(false);

        setDataEditText(etPhonePay,x);
        setDataEditText(etPaytm,p);
        setDataEditText(etTez,tz);
        setDataEditText(etPAddress,ad);
        setDataEditText(etPCity,ct);
        setDataEditText(etPPinCode,pn);
        setDataEditText(etAccNo,ac);
        setDataEditText(etBankName,bn);
        setDataEditText(etIfscCode,ic);
        setDataEditText(etAccHolderName,ah);


       // filldetail();
    }

    private void storeBankDetails(final String accno,final String bankname,final String ifsc,final String hod_name,final String mailid) {

        progressDialog.show();
        Map<String,String> params=new HashMap<>();
        params.put("key","3");
        params.put("user_id",mailid);
        params.put("accountno",accno);
        params.put("bankname",bankname);
        params.put("ifsc",ifsc);
        params.put("accountholder",hod_name);

        CustomJsonRequest customJsonRequest=new CustomJsonRequest(Request.Method.POST, URL_REGISTER, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try
                {
                    boolean success=response.getBoolean("responce");
                    if(success)
                    {
                        progressDialog.dismiss();
                        //Prevalent.currentOnlineuser.setAccountno(accno);
                        //Prevalent.currentOnlineuser.setBank_name(bankname);
                        //Prevalent.currentOnlineuser.setIfsc_code(ifsc);
                        //Prevalent.currentOnlineuser.setAccount_holder_name(hod_name);
                        sessionMangement.updateAccSection(accno,bankname,ifsc,hod_name);
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        return;
                    }


                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    common.showToast("Something Went Wrong");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                String msg=common.VolleyErrorMessage(error);
                if(!msg.isEmpty())
                {
                    common.showToast(msg);
                }
            }
        });

        AppController.getInstance().addToRequestQueue(customJsonRequest,"add_bank_json");

    }

    private void storeAddressData(final String a,final String c,final String p,final String user_id) {

        progressDialog.show();
        String json_tag="add_address";
        Map<String,String> params=new HashMap<>();
        params.put("key","2");
        params.put("address",a);
        params.put("city",c);
        params.put("pin",p);
        params.put("user_id",user_id);
        CustomJsonRequest customJsonRequest=new CustomJsonRequest(Request.Method.POST, URL_REGISTER, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressDialog.dismiss();
                try
                {
                    boolean success=response.getBoolean("responce");
                    if(success)
                    {
                        progressDialog.dismiss();
                       // Prevalent.currentOnlineuser.setAddress(a);
                        //Prevalent.currentOnlineuser.setCity(c);
                        //Prevalent.currentOnlineuser.setPincode(p);
                        sessionMangement.updateAddressSection(a,c,p);
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        return;
                    }


                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                String msg=common.VolleyErrorMessage(error);
                if(!msg.isEmpty())
                {
                    common.showToast(msg);
                }
            }
        });

        AppController.getInstance().addToRequestQueue(customJsonRequest,json_tag);

    }
    private void selectDate() {
        final Calendar c = Calendar.getInstance();
        year = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                year  = year;
                month = month;
                day   = dayOfMonth;

                // Show selected date
                et_dob.setText(new StringBuilder().append(day).append("-")
                        .append(month + 1) .append("-").append(year)
                        .append(" "));

            }
        },year,month,day);
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis() - 568025136000L);
        datePickerDialog.show();
        datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE).setTextColor(Color.BLUE);
        datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE).setTextColor(Color.BLUE);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id ==R.id.add_bank)
        {



                    String accno=etAccNo.getText().toString().trim();
                    String bankname=etBankName.getText().toString().trim();
                    String ifsc=etIfscCode.getText().toString().trim();
                    String hod_name=etAccHolderName.getText().toString().trim();

                    if(TextUtils.isEmpty(accno))
                    {
                        etAccNo.setError("Enter your account number");
                        etAccNo.requestFocus();
                        return;

                    }
                    else if(TextUtils.isEmpty(bankname))
                    {
                        etBankName.setError("Enter Bank Name");
                        etBankName.requestFocus();
                        return;

                    } else if(TextUtils.isEmpty(ifsc))
                    {
                        etIfscCode.setError("Enter ifsc code");
                        etIfscCode.requestFocus();
                        return;

                    } else if(TextUtils.isEmpty(hod_name))
                    {
                        etAccHolderName.setError("Enter acc holder name");
                        etAccHolderName.requestFocus();
                        return;

                    }
                    else
                    {
//                        String mailid= Prevalent.currentOnlineuser.getId().toString();
                        String mailid= sessionMangement.getUserDetails().get(KEY_ID).toString();
                        //                       Toast.makeText(DrawerProfileActivity.this,"Email :"+mailid,Toast.LENGTH_LONG).show();
                        storeBankDetails(accno,bankname,ifsc,hod_name,mailid);
                    }


                }

        else if (id == R.id.add_address)
        {
            String a=etPAddress.getText().toString().trim();
            String c=etPCity.getText().toString().trim();
            String p=etPPinCode.getText().toString().trim();

            if(TextUtils.isEmpty(a))
            {
                etPAddress.setError("Enter your Address");
                etPAddress.requestFocus();
                return;

            }
            else if(TextUtils.isEmpty(c))
            {
                etPCity.setError("Enter city name");
                etPCity.requestFocus();
                return;

            } else if(TextUtils.isEmpty(p))
            {
                etPPinCode.setError("Enter pin code");
                etPPinCode.requestFocus();
                return;

            }
            else
            {
//                String user_id= Prevalent.currentOnlineuser.getId().toString();
                String user_id= sessionMangement.getUserDetails().get(KEY_ID).toString();
                //                       Toast.makeText(DrawerProfileActivity.this,"Email :"+mailid,Toast.LENGTH_LONG).show();
                storeAddressData(a,c,p,user_id);
            }


        }
        else if (id== R.id.add_paytm)
        {
            String phonepaynumber=etPhonePay.getText().toString().trim();
            String teznumber=etTez.getText().toString().trim();
            String paytmNumber=etPaytm.getText().toString().trim();
            if(TextUtils.isEmpty(paytmNumber))
            {
                etPaytm.setError("Enter Paytm Number");
                etPaytm.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(teznumber))
            {
                etTez.setError("Enter GooglePay Number");
                etTez.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(phonepaynumber))
            {
                etPhonePay.setError("Enter Phone Pay Number");
                etPhonePay.requestFocus();
                return;
            }
            else
            {
//                String user_id= Prevalent.currentOnlineuser.getId().toString();
                String user_id= sessionMangement.getUserDetails().get(KEY_ID).toString();
                //                       Toast.makeText(DrawerProfileActivity.this,"Email :"+mailid,Toast.LENGTH_LONG).show();
                storeAccDetails(teznumber,paytmNumber,phonepaynumber,user_id);
            }
        }
        else if (id == R.id.et_dob)
        {
            selectDate();
        }

        else if (id == R.id.btn_update)
        {
            String email=et_email.getText().toString().trim();
            String mobile=et_mobile.getText().toString().trim();
            String dob=et_dob.getText().toString().trim();
            if(TextUtils.isEmpty(mobile))
            {
                et_mobile.setError("Enter Mobile Number");
                et_mobile.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(email))
            {
                et_email.setError("Enter email Address");
                et_email.requestFocus();
                return;
            }
            else if (!email.matches(emailPattern))
            {
                et_email.setError("Enter valid email Address");
                et_email.requestFocus();
                return;
            }
            else if(TextUtils.isEmpty(dob))
            {
                et_dob.setError("Enter Date of Birth");
                et_dob.requestFocus();
                return;
            }
            else
            {
//                String user_id= Prevalent.currentOnlineuser.getId().toString();
                String user_id= sessionMangement.getUserDetails().get(KEY_ID).toString();
                //                       Toast.makeText(DrawerProfileActivity.this,"Email :"+mailid,Toast.LENGTH_LONG).show();
                storeProfileData(dob ,user_id,email);
            }
        }


        }
    public void setDataEditText(EditText edt,String data)
    {
        String s=data.toString();
        if(data == null || data.isEmpty() || data.equalsIgnoreCase("null"))
        {

        }
        else
        {
            edt.setText(data.toString());
        }
    }

    private void storeAccDetails(final String teznumber, final String paytmno , final String phonepay, final String mailid) {


        progressDialog.show();
        Map<String,String> params=new HashMap<>();
        params.put("key","4");
        params.put("user_id",mailid);
        params.put("tez",teznumber);
        params.put("paytm",paytmno);
        params.put("phonepay",phonepay);

        CustomJsonRequest customJsonRequest=new CustomJsonRequest(Request.Method.POST, URL_REGISTER, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    boolean success=response.getBoolean("responce");
                    if(success)
                    {
                        progressDialog.dismiss();
                       // Prevalent.currentOnlineuser.setTez_no(teznumber);
                      //  Prevalent.currentOnlineuser.setPaytm_no(paytmno);
                      //  Prevalent.currentOnlineuser.setPhonepay_no(phonepay);
                        sessionMangement.updatePaymentSection(teznumber,paytmno,phonepay);
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        String msg=response.getString("message");
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        return;
                    }

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    progressDialog.dismiss();
                    common.showToast(wrong);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                String msg=common.VolleyErrorMessage(error);
                if(!msg.isEmpty())
                {
                    common.showToast(msg);
                }
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonRequest,"json_tez");

    }

    private void storeProfileData(final String dob , String user_id , final String email)
    {   progressDialog.show();
        Map<String,String> params=new HashMap<>();
        params.put("key","5");
        params.put("email",email);
        params.put("dob",dob);
        params.put("user_id",user_id);

   Log.e("asdasdasdadasd",""+params.toString());

        CustomJsonRequest customJsonRequest=new CustomJsonRequest(Request.Method.POST, URL_REGISTER, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Log.e("asdasd",""+response.toString());
                    boolean success=response.getBoolean("responce");
                    if(success)
                    {
                        progressDialog.dismiss();
                       //Prevalent.currentOnlineuser.setEmail(email);
                       // Prevalent.currentOnlineuser.setDob(dob);
                        String m =  sessionMangement.getUserDetails().get(KEY_MOBILE);
                        sessionMangement.updateAddressSections(m,email,dob);
                        String msg=response.getString("message");
                        Log.e("snwbdef",""+msg.toString());
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                    }
                    else
                    {
                        progressDialog.dismiss();
                        String msg=response.getString("message");
                        Log.e("snwjh",""+msg.toString());
                        Toast.makeText(ProfileActivity.this, ""+msg, Toast.LENGTH_SHORT).show();

                        return;
                    }

                }
                catch (Exception ex)
                {
                    ex.printStackTrace();
                    progressDialog.dismiss();
                    common.showToast(wrong);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                String msg=common.VolleyErrorMessage(error);
                if(!msg.isEmpty())
                {
                    common.showToast(msg);
                }
            }
        });
        AppController.getInstance().addToRequestQueue(customJsonRequest,"json_tez");

    }

    public void filldetail()
    {

//        TextView tv_profile,tv_address,tv_bank_detail,tv_account;
//        LinearLayout ll_profile,ll_address,ll_bank_detail,ll_account;

        tv_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll_profile.setVisibility(View.VISIBLE);
                tv_profile.setVisibility(View.GONE);
                ll_address.setVisibility(View.GONE);
                ll_bank_detail.setVisibility(View.GONE);
                tv_account.setVisibility(View.GONE);
                tv_address.setVisibility(View.VISIBLE);
                tv_bank_detail.setVisibility(View.VISIBLE);
                tv_account.setVisibility(View.VISIBLE);

            }
        });

        tv_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               tv_address.setVisibility(View.GONE);
               ll_address.setVisibility(View.VISIBLE);
               tv_profile.setVisibility(View.VISIBLE);
               tv_bank_detail.setVisibility(View.VISIBLE);
               tv_account.setVisibility(View.VISIBLE);
               ll_account.setVisibility(View.GONE);
               ll_bank_detail.setVisibility(View.GONE);
               ll_profile.setVisibility(View.GONE);


            }
        });

        tv_bank_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_bank_detail.setVisibility(View.GONE);
                ll_bank_detail.setVisibility(View.VISIBLE);
                tv_profile.setVisibility(View.VISIBLE);
                tv_address.setVisibility(View.VISIBLE);
                tv_account.setVisibility(View.VISIBLE);
                ll_account.setVisibility(View.GONE);
                ll_address.setVisibility(View.GONE);
                ll_profile.setVisibility(View.GONE);

            }
        });

        tv_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_account.setVisibility(View.GONE);
                ll_account.setVisibility(View.VISIBLE);
                tv_profile.setVisibility(View.VISIBLE);
                tv_bank_detail.setVisibility(View.VISIBLE);
                tv_address.setVisibility(View.VISIBLE);
                ll_address.setVisibility(View.GONE);
                ll_bank_detail.setVisibility(View.GONE);
                ll_profile.setVisibility(View.GONE);
            }
        });
    }

}

