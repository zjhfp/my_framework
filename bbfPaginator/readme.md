数据管家——Configuration：
	MyBatis在运行期的基本所有的数据都会汇总到这个类。
	sqlFragments：定义在mapper里的sql标签或注解内容
	resultMaps：定义在mapper或者注解的resultMap内容
	mappedStatements：通过键值对的方式存储，
		key：配置的id属性加上namespace
		value：MappedStatement对象，这个对象对应了配置的select/update/delete/insert标签的值
	MappedStatement对象包含
		这条sql语句的ID，
		执行的类型(insert,update...)，
		statementType(指定产生Statemnet的类型，如：PreparedStatement)
		还有SqlSource接口的子对象，MyBatis有两种SqlSource：动态的和静态的
		还包含ParameterMap：这直接关系你定义的SQL语句中通过#{propertyName}定义的动态填充值。
		注意：此时的MapperStatement中的SQL语句还是带有#{propertyName}这样占位符的字符串，还并没有解析成带问号的占位符

mybatis是逻辑分页的，即通过用户查询，将结果缓存，再查看是否传递了RowBounds对象，
再查看里面的offset和limit值，通过这两个值，从返回的结果集中截取位于期间的值。
最常用的实现物理分页方法：
	添加MyBatis插件，实现Interceptor接口，拦截StatementHandler接口中的prepare方法，
	再拦截ResultSetHandler接口的handlerResultSet方法，
	但是这种方法并没有将分页的offset和limit值让MyBatis动态的添加到SQL中
		