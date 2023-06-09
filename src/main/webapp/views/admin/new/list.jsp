<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-new"/>
<c:url var="NewURL" value="/admin-new"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Danh sách bài viết</title>
</head>
<body>
	<div class="main-content">
		<form action='<c:url value="/admin-new" />' id="formSubmit"
			method="get">

			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs">
					<ul class="breadcrumb">
						<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
								chủ</a></li>
					</ul>
					<!-- /.breadcrumb -->
				</div>
				<div class="page-content">
					<div class="row">
						<div class="col-xs-12">
							<c:if test="${not empty messageResponse}">
								<div class="alert alert-${alert}">${messageResponse}</div>
							</c:if>
							<div class="widget-box table-filter">
								<div class="table-btn-controls">
									<div class="pull-right tableTools-container">
										<div class="dt-buttons btn-overlap btn-group">
											<a flag="info"
												class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Thêm bài viết'
												href='<c:url value="/admin-new?type=edit"/>'> <span>
													<i class="fa fa-plus-circle bigger-110 purple"></i>
											</span>
											</a>
											<button id="btnDelete" type="button"
												class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
												data-toggle="tooltip" title='Xóa bài viết'>
												<span> <i class="fa fa-trash-o bigger-110 pink"></i>
												</span>
											</button>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>
												<input class="form-check-input" type="checkbox"
												value="" id="checkAll" name="checkAll" />
											</th>
											<th>Tên bài viết</th>
											<th>Mô tả ngắn</th>
											<th>Thao tác</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="item" items="${model.listResult}">
											<tr>
												<td>
													<input class="form-check-input" type="checkbox"
													value="${item.id}" id="checkbox_${item.id}"  />
												</td>
												<td>${item.title}</td>
												<td>${item.shortDescription}</td>
												<td><c:url var="editUrl" value="/admin-new">
														<c:param name="type" value="edit" />
														<c:param name="id" value="${ item.id }" />
													</c:url> <a class="btn btn-sm btn-primary btn-edit"
													data-toggle="tooltip" title="Cập nhật bài viết"
													href='${ editUrl }'><i class="fa fa-pencil-square-o"
														aria-hidden="true"></i> </a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
								<ul class="pagination" id="pagination"></ul>
								<input type="hidden" value="" id="page" name="page" /> <input
									type="hidden" value="" id="maxPageItem" name="maxPageItem" />
								<input type="hidden" value="" id="sortName" name="sortName" />
								<input type="hidden" value="" id="sortBy" name="sortBy" /> <input
									type="hidden" value="" id="type" name="type" />
							</div>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
	<script>
		var totalPages = ${model.totalPage};
		var currentPage = ${model.page};
		var limit = 2;
		$(function() {
			window.pagObj = $('#pagination').twbsPagination({
				totalPages : totalPages,
				visiblePages : 10,
				startPage : currentPage,
				onPageClick : function(event, page) {
					//console.info(page + ' (from options)');
					if (currentPage != page) {
						$('#maxPageItem').val(limit);
						$('#page').val(page);
						$('#sortName').val('title');
						$('#sortBy').val('desc');
						$('#type').val('list');
						$('#formSubmit').submit();
					}
				}
			});
		});
		$('#btnDelete').click(function() {
				
			Swal.fire({
				  title: 'Are you sure?',
				  text: "You won't be able to revert this!",
				  icon: 'warning',
				  showCancelButton: true,
				  confirmButtonColor: '#3085d6',
				  cancelButtonColor: '#d33',
				  confirmButtonText: 'Yes, delete it!'
				}).then((result) => {
				  if (result.isConfirmed) {
					  Swal.fire(
						      'Deleted!',
						      'Your file has been deleted.',
						      'success'
						    )
					  var data = {};
						var ids = $('tbody input[type=checkbox]:checked').map(function () {
				            return $(this).val();
				        }).get();
						if(ids == ""){
							window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
						}else{
							data['ids'] = ids;
							deleteNew(data);
						}

				   
				  }
				})
				
			
				
			
			
		});
		$('#checkAll').click(function(event) {   
		    if(this.checked) {
		        // Iterate each checkbox
		        $(':checkbox').each(function() {
		            this.checked = true;                        
		        });
		    } else {
		        $(':checkbox').each(function() {
		            this.checked = false;                       
		        });
		    }
		}); 
		function deleteNew(data) {
			$.ajax({
				url : '${APIurl}',
				type : 'DELETE',
				contentType : 'application/json',
				data : JSON.stringify(data) , 
				success : function (result) {
					window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=delete_success";
				},
				error : function (error) {
					window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
				}
			});
		}
		
	</script>
</body>
</html>