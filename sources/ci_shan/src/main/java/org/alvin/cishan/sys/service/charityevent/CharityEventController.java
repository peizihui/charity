package org.alvin.cishan.sys.service.charityevent;

import org.alvin.cishan.common.controller.PrincipalAction;
import org.alvin.cishan.sys.auth.adminsysuser.AdminSysUser;
import org.alvin.cishan.sys.util.Page;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @类说明: 善行活动--数据控制器层
 * @author: 唐植超
 * @date : 2018-10-20 11:58:00
 **/
@RestController
@RequestMapping("api/charityEvent")
public class CharityEventController extends PrincipalAction {

	@SuppressWarnings("unused")
	private final Log logger = LogFactory.getLog(getClass());
	@Autowired
	private CharityEventService service; //注入善行活动数据逻辑层

	/**
	 * @方法说明： 新增[善行活动]记录
	 */
	@PostMapping("save")
	public int save(@RequestBody CharityEvent charityEvent, Principal principal) {
		AdminSysUser user = getUser(principal);
		charityEvent.setAuthor(user.getUser_id().longValue());
		charityEvent.setStatus((byte) 1);
		charityEvent.setCreate_time(new Date());
		return service.save(charityEvent);
	}

	/**
	 * @方法说明： 删除善行活动记录(多条)
	 */
	@RequestMapping("delete")
	public int delete(@RequestParam("id") Long id) {
		return service.delete(new Long[]{id});
	}

	/**
	 * @方法说明： 修改善行活动记录
	 */
	@PostMapping("update")
	public int update(@RequestBody CharityEvent charityEvent) {
		return service.update(charityEvent);
	}

	/**
	 * @方法说明： 按条件查询分页善行活动列表
	 */
	@PostMapping("queryPage")
	public Page<CharityEvent> queryPage(@RequestBody CharityEventCond cond) {
		return service.queryPage(cond);
	}

	/**
	 * @方法说明： 按条件查询不分页善行活动列表
	 */
	@PostMapping("queryList")
	public List<CharityEvent> queryList(@RequestBody CharityEventCond cond) {
		return service.queryList(cond);
	}

	/**
	 * @方法说明： 按主键查单个善行活动记录
	 */
	@PostMapping("findById")
	public CharityEvent findById(@RequestParam("id") Long id) {
		return service.findById(id);
	}

	/**
	 * @方法说明： 按条件查询善行活动记录个数
	 */
	@PostMapping("queryCount")
	public long queryCount(@RequestBody CharityEventCond cond) {
		return service.queryCount(cond);
	}
}