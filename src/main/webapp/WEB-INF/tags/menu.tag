<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="petclinic" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%@ attribute name="name" required="true" rtexprvalue="true"
	description="Name of the active menu: home, owners, vets or error"%>

<nav class="navbar navbar-default" role="navigation">
	<div class="container">
		
		<div class="navbar-collapse collapse" id="main-navbar">
			<ul class="nav navbar-nav">

				

				<petclinic:menuItem active="${name eq 'home'}" url="/"
					title="home page">
					<span class="glyphicon glyphicon-home" aria-hidden="true"></span>
					<span>Inicio</span>
				</petclinic:menuItem>
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'partidasNueva'}" url="/partidas/create"
					title="Nueva partida">
					<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
					<span>Nueva partida</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
					<petclinic:menuItem active="${name eq 'partidasCurso'}" url="/partidas/enCurso"
						title="ver partidas en curso">
						<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
						<span>Partidas en curso</span>
					</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'partidasFin'}" url="/partidas/finalizadas"
					title="ver partidas finalizadas">
					<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
					<span>Partidas finalizadas</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('admin')">
				<petclinic:menuItem active="${name eq 'users'}" url="/users/all"
					title="Lista de usuarios">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					<span>Usuarios</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('jugador')">
				<petclinic:menuItem active="${name eq 'logros'}" url="/jugador/logros/"
					title="Lista de usuarios">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					<span>Mis logros</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="hasAuthority('jugador')">
				<petclinic:menuItem active="${name eq 'stats'}" url="/jugador/estadisticas/"
					title="Lista de usuarios">
					<span class="glyphicon glyphicon-stats" aria-hidden="true"></span>
					<span>Mis estadisticas</span>
				</petclinic:menuItem>
				</sec:authorize>

				<sec:authorize access="hasAuthority('jugador')">
				<petclinic:menuItem active="${name eq 'partidasFinalizadasUsuario'}" url="/partidas/jugador/"
					title="Lista de usuarios">
					<span class="glyphicon glyphicon-list" aria-hidden="true"></span>
					<span>Mis partidas</span>
				</petclinic:menuItem>
				</sec:authorize>
				
				<sec:authorize access="isAuthenticated()">
				<petclinic:menuItem active="${name eq 'ranking'}" url="/ranking"
					title="Lista de usuarios">
					<span class="glyphicon glyphicon-flag" aria-hidden="true"></span>
					<span>Ranking</span>
				</petclinic:menuItem>
				</sec:authorize>


			</ul>

			<ul class="nav navbar-nav navbar-right">
				<sec:authorize access="!isAuthenticated()">
					<li><a href="<c:url value="/login" />">Login</a></li>
					<li><a href="<c:url value="/jugador/new" />">Register</a></li>
				</sec:authorize>
				<sec:authorize access="isAuthenticated()">
					<li class="dropdown"><a href="#" class="dropdown-toggle"data-toggle="dropdown"> 
					<span class="glyphicon glyphicon-user"></span>
							<strong><sec:authentication property="name" /></strong> 
							<span class="glyphicon glyphicon-chevron-down"></span>
					</a>
						<ul class="dropdown-menu">
							<li>
								<div class="navbar-login">
									<div class="row">
										<div class="col-6 h5">
											<p class="text-center">
												<a href="<c:url value="/jugador/perfil" />"
													class="btn btn-info btn-sm"><span class="glyphicon glyphicon-share-alt"></span> Ver perfil</a>
											</p>
											<p class="col-6 h5 text-center">
												<a href="<c:url value="/logout" />"
													class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span> Logout</a>
											</p>
										</div>
									</div>
								</div>
							</li>
							<li class="divider"></li>
<!-- 							
                            <li> 
								<div class="navbar-login navbar-login-session">
									<div class="row">
										<div class="col-lg-12">
											<p>
												<a href="#" class="btn btn-info btn-block">My Profile</a>
												<a href="#" class="btn btn-danger btn-block">Change
													Password</a>
											</p>
										</div>
									</div>
								</div>
							</li>
-->
						</ul></li>
				</sec:authorize>
			</ul>
		</div>



	</div>
</nav>