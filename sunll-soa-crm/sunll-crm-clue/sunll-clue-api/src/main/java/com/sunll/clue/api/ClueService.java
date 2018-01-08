package com.sunll.clue.api;

import com.sunll.clue.entity.business.*;
import com.sunll.common.api.ApiAcceptData;
import com.sunll.common.api.ApiSendData;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;


/**
 * Created by double on 2017/12/4.
 */

public interface ClueService {

    ApiSendData listClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData listClueApp(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    Map<String,Object> exportClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    Map<String,Object> exportClueExcel(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    ApiSendData insertClueUserShowField(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取线索详情
     * @param apiSendData
     * @param clueId
     * @return
     */
    ApiSendData getClueDetail(ApiSendData apiSendData, ApiAcceptData clueId);

    /**
     * 新建线索
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData createClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 保存线索
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData saveClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 更新线索
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData updateEditClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 转移线索
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData transferClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 发送短信
     * @param apiSendData
     * @param userId
     * @param clueId
     * @param contentId
     * @param content
     * @param signature
     * @return
     */
    ApiSendData sendMsg(ApiSendData apiSendData, Integer userId, List<String> clueId, Integer contentId,String content,
    String signature);

    /**
     * 显示短息模板
     * @param apiSendData
     * @param companyId
     * @return
     */
    ApiSendData showMessageTemplate(ApiSendData apiSendData, Integer companyId);

    /**
     * 根据组织ids和企业id获取所有的业务
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData selectBusiness(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 改变跟进记录状态
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData changeFollowStatus(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 删除线索
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData delClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取短信模板
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData getSmsTem(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 拨打线索电话
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData callClue(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取跟进记录或者是获取指向门店
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData getFollowRecordOrPointStore(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取线索详情移动端表头接口
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData getClueDetailApi (ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 获取线索的系统信息
     * @param apiSendData
     * @param apiAcceptData
     * @return
     */
    ApiSendData getClueSystemInfo(ApiSendData apiSendData, ApiAcceptData apiAcceptData);

    /**
     * 导入线索数据校验
     * @param
     * @return
     */
    String validateData(Integer businessTypeId,String headerName,String value);

    List<BusinessType> selectByBusinessId(Integer businessId);

    List<BusinessTypeField> selectByBusinessTypeIdAndTableHeader(Integer businessTypeId,String tableHeader);

    CompanyOrganizationBusiness selectByCompanyId(int i);

    void batchSaveClues(Integer userId,Integer businessTypeId,Integer businessId,List<List> list,String checkBox);

    ApiSendData checkBusinessStatus(ApiSendData apiSendData,ApiAcceptData apiAcceptData);

    MessageTemplate getMessageTemplate(Integer id);

    boolean judgeIsRequired(Integer businessTypeId, String s);

    String[] getFieldIdByHeader(String[] tableHeader,Integer businessTypeId);

    Integer getBusinessTypeId(Integer businessId);
}
