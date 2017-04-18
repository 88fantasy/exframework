<!-- 工具栏 -->
<#list tbs![]>
<nav class="navbar navbar-light bg-faded">
	<#items as tb>
    <button id="toolbar-#{tb.buttonid}" class="btn ${tb.iconcls!'btn-info btn-outline'} need-tooltip" type="button" data-placement="bottom" title="tooltip" data-animation="true" onclick="${tb.texthandler}(this);" >${tb.title}</button>
    </#items>
</nav>
</#list>



<div id="gridtable" class="table-responsive">
	<div class="row col-xs-12 grid-table">
		<table class="table table-hover table-striped"
			data-page-length='25'>
			<thead>
				<tr>
					<th>#</th>
					<th>Company</th>
					<th>Country</th>
					<th>Status</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">1</th>
					<td>Facebook</td>
					<td>Mexico</td>
					<td><span class="tag tag-danger">danger</span></td>
				</tr>
				<tr>
					<th scope="row">2</th>
					<td>LG Electronics</td>
					<td>France</td>
					<td><span class="tag tag-danger">danger</span></td>
				</tr>
				<tr>
					<th scope="row">3</th>
					<td>Pinterest</td>
					<td>Sweden</td>
					<td><span class="tag tag-success">success</span></td>
				</tr>
				<tr>
					<th scope="row">4</th>
					<td>Google Inc.</td>
					<td>USA</td>
					<td><span class="tag tag-warning">warning</span></td>
				</tr>
				<tr>
					<th scope="row">5</th>
					<td>Uber</td>
					<td>England</td>
					<td><span class="tag tag-danger">danger</span></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="row col-xs-12 grid-footer-bar" aria-label="Grid Footer">
		<ul class="grid-footer-bar-ul">
			<li class="gird-footer-item grid-footer-pagination">
				<ul class="pagination">
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Previous"> <span aria-hidden="true">&laquo;</span>
							<span class="sr-only">Previous</span>
					</a></li>
					<li class="page-item"><a class="page-link" href="#">1</a></li>
					<li class="page-item"><a class="page-link" href="#">2</a></li>
					<li class="page-item"><a class="page-link" href="#">3</a></li>
					<li class="page-item"><a class="page-link" href="#">4</a></li>
					<li class="page-item"><a class="page-link" href="#">5</a></li>
					<li class="page-item"><a class="page-link" href="#"
						aria-label="Next"> <span aria-hidden="true">&raquo;</span>
							<span class="sr-only">Next</span>
					</a></li>
				</ul>
			</li>
			<li class="gird-footer-item grid-footer-pagesize-selector">
				<label> 每页显示 <select name="example_length"
					aria-controls="example" class="form-control input-sm"><option
							value="50">50</option>
						<option value="100">100</option>
						<option value="500">500</option>
						<option value="infinity">∞</option></select>
				</label>
			</li>
			<li class="gird-footer-item grid-footer-col-selector">
				<button type="button" class="btn btn-info btn-outline">调整字段</button>
			</li>
		</ul>
	</div>
</div>