package com.ischoolbar.programmer.page.admin;

import org.springframework.stereotype.Component;

/**
 * 分页基本信息
 * @author yangrundong
 *
 */

@Component
public class Page {
		private int page = 1; //当前页面
		private int rows ; //每页显示的数量
		
		private int  offset; //对于数控库的便宜量

		public int getPage() {
			return page;
		}

		public void setPage(int page) {
			this.page = page;
		}

		public int getRows() {
			return rows;
		}

		public void setRows(int rows) {
			this.rows = rows;
		}

		public int getOffset() {
			this.offset = (page-1)*rows;
			
			return offset;
		}

		public void setOffset(int offset) {
			this.offset = offset;
		}
		
}
