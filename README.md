# CheckBoxCustom
체크박스 커스텀 뷰 사용법

체크박스    
app:c_height="@dimen/c_heightSize"    
app:c_width="@dimen/c_widthSize"    
app:c_checkedBackground="@drawable/unchecked"   
app:c_uncheckedBackground="@drawable/unchecked"   

텍스트
app:t_color="#875353"   
app:t_font="@font/dnfd"   
app:t_fontSize="@dimen/t_fontSize"    
app:t_text="아무 텍스트나 입력해주세요"   

wrap_content를 원하는 경우는 wrap을 사용하고    
원하는 값을 입력할때는 @dimen/t_width, @dimen/t_height를 입력하면 됩니다    
app:t_width="wrap" or app:t_width="@dimen/t_width"    
app:t_height="wrap" or app:t_height="@dimen/t_height"
