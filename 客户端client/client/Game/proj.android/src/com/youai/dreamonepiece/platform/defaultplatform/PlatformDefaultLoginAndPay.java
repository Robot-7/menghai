package com.youai.dreamonepiece.platform.defaultplatform;

import android.app.Activity;

import com.youai.DeviceUtil;
import com.youai.IGameActivity;
import com.youai.IPlatformLoginAndPay;
import com.youai.PlatformAndGameInfo;
import com.youai.PlatformAndGameInfo.GameInfo;
import com.youai.PlatformAndGameInfo.LoginInfo;
import com.youai.PlatformAndGameInfo.PayInfo;
import com.youai.PlatformAndGameInfo.ShareInfo;
import com.youai.PlatformAndGameInfo.VersionInfo;
import com.youai.dreamonepiece.GameInterface.IGameAppStateCallback;
import com.youai.dreamonepiece.GameInterface.IGameUpdateStateCallback;
import com.youai.dreamonepiece.GameInterface.IPlatformSDKStateCallback;

public class PlatformDefaultLoginAndPay implements IPlatformLoginAndPay {

	
private static final String TAG = PlatformDefaultLoginAndPay.class.getSimpleName();
	
	private IGameActivity 				mGameActivity;
	private IPlatformSDKStateCallback	mCallback1;
	private IGameUpdateStateCallback	mCallback2;
	private IGameAppStateCallback		mCallback3;
	
	private Activity 					game_ctx = null;
	private GameInfo 					game_info = null;
	private LoginInfo 					login_info = null;
	private VersionInfo 				version_info = null;
	private PayInfo 					pay_info = null;
	
	private int 						auto_recalllogin_count = 0;
	
	
	private static PlatformDefaultLoginAndPay sInstance = null;
	public static PlatformDefaultLoginAndPay getInstance() {
		if (sInstance == null) {
			sInstance = new PlatformDefaultLoginAndPay();
		}
		return sInstance;
	}
	
	private PlatformDefaultLoginAndPay() {
		
	}
	
	@Override
	public void init(IGameActivity game_acitivity, GameInfo game_info) {
		

		mGameActivity = game_acitivity;
		this.game_ctx = game_acitivity.getActivity();
		this.game_info = game_info;
		game_info.use_platform_sdk_type = 1;//0逻辑需要再调
		game_info.debug_mode = PlatformAndGameInfo.enDebugMode_Release;
		 	
		//
		mCallback1.notifyInitPlatformSDKComplete();
		//
		
	}

	@Override
	public void setPlatformSDKStateCallback(IPlatformSDKStateCallback callback1) {
		// TODO Auto-generated method stub

		this.mCallback1 = callback1;
		
	}

	@Override
	public void setGameUpdateStateCallback(IGameUpdateStateCallback callback2) {
		// TODO Auto-generated method stub
		this.mCallback2 = callback2;
	}

	@Override
	public void setGameAppStateCallback(IGameAppStateCallback callback3) {
		// TODO Auto-generated method stub
		this.mCallback3 = callback3;
	}

	@Override
	public int isSupportInSDKGameUpdate() {
		// TODO Auto-generated method stub
		return PlatformAndGameInfo.DoNotSupportUpdate;
	}

	@Override
	public int getPlatformLogoLayoutId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void unInit() {
		// TODO Auto-generated method stub
		mGameActivity = null;
		mCallback1 = null;
		mCallback2 = null;
		mCallback3 = null;
		//
		this.game_ctx = null;
		this.game_info = null;
		this.login_info = null;
		this.version_info = null;
		this.pay_info = null;

	}

	@Override
	public GameInfo getGameInfo() {
		// TODO Auto-generated method stub
		return this.game_info;
	}

	@Override
	public void callLogin() {
		
		mCallback3.showWaitingViewImp(true, -1, "正在登录");
		
		LoginInfo login_info = new LoginInfo();
		login_info.login_result = PlatformAndGameInfo.enLoginResult_Success;
		login_info.login_session = "";
		login_info.account_uid_str = DeviceUtil.getDeviceUUID(this.game_ctx);
		login_info.account_uid = 0l;
		login_info.account_nick_name = DeviceUtil.getDeviceProductName(this.game_ctx);
		//
		notifyLoginResult(login_info);
		
		mCallback3.showWaitingViewImp(false, -1, "已登录");
	}

	@Override
	public void notifyLoginResult(LoginInfo login_result) {
		
		login_info = null;
		login_info = login_result;
		//
		if (login_result != null) {
			
			login_info.account_uid_str = PlatformAndGameInfo.enPlatformShort_Default+login_info.account_uid_str;
			
			mCallback3.notifyLoginResut(login_result);
		}
	}

	@Override
	public LoginInfo getLoginInfo() {
		// TODO Auto-generated method stub
		return login_info;
	}

	@Override
	public void callLogout() {
		// TODO Auto-generated method stub

	}

	@Override
	public void callCheckVersionUpate() {
		// TODO Auto-generated method stub

	}

	@Override
	public void notifyVersionUpateInfo(VersionInfo version_info) {
		// TODO Auto-generated method stub

	}

	@Override
	public int callPayRecharge(PayInfo pay_info) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void notifyPayRechargeRequestResult(PayInfo pay_info) {
		// TODO Auto-generated method stub

	}

	@Override
	public void callAccountManage() {
		// TODO Auto-generated method stub

	}

	@Override
	public String generateNewOrderSerial() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void callPlatformFeedback() {
		// TODO Auto-generated method stub

	}

	@Override
	public void callPlatformSupportThirdShare(ShareInfo share_info) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void callPlatformGameBBS() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGamePause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameResume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onGameExit() {
		// TODO Auto-generated method stub

	}

}
